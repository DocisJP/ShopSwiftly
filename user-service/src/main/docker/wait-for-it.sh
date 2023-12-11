#!/bin/bash

# Determine the environment - default to 'local' if ENVIRONMENT is not set
ENVIRONMENT=${ENVIRONMENT:-local}

# Configuration for Eureka based on the environment
if [ "$ENVIRONMENT" == "local" ]; then
    eureka_url="http://eureka-server:8761"
    db_host="user_db"  # Use the Docker Compose service name for PostgreSQL
    db_port="5432"     # Default PostgreSQL port
else
    # Use environment variables set in Render for cloud deployment
    eureka_url="${EUREKA_SERVER_URL}"
    # Parse database URL from Render's external database URL
    # Assumes format: postgres://<username>:<password>@<host>/<dbname>
    db_url="${DATABASE_URL}"
    db_host=$(echo $db_url | cut -d '@' -f 2 | cut -d '/' -f 1)
    db_port="5432"  # Default PostgreSQL port
    # Extract the username and password from the URL
    db_user=$(echo $db_url | cut -d '/' -f 3 | cut -d ':' -f 1)
    db_pass=$(echo $db_url | cut -d ':' -f 3 | cut -d '@' -f 1)
fi

max_attempts=15
attempt_interval=5

# Function to check Eureka server status
check_eureka() {
  response=$(curl --write-out '%{http_code}' --silent --output /dev/null "${eureka_url}/actuator/health")
  if [ "$response" -eq 200 ]; then
    echo "Eureka server is up!"
    return 0
  else
    echo "Eureka server not yet available..."
    return 1
  fi
}

# Wait for Eureka server to be available
echo "Waiting for Eureka server to be available..."
attempt=1
until check_eureka; do
  if [ ${attempt} -ge ${max_attempts} ]; then
    echo "Eureka server not available after ${max_attempts} attempts. Exiting."
    exit 1
  fi

  printf "Attempt %s/%s: Eureka server not ready yet. Waiting %s seconds...\n" "${attempt}" "${max_attempts}" "${attempt_interval}"
  sleep ${attempt_interval}
  attempt=$(( attempt + 1 ))
done

# Wait for PostgreSQL to be up
echo "Waiting for PostgreSQL..."
until nc -z ${db_host} ${db_port}; do
    sleep 10
done
echo "PostgreSQL is up!"

# Now that both Eureka and PostgreSQL are up, execute the command
echo "Executing command..."
exec $cmd
