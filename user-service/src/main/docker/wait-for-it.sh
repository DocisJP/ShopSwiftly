#!/bin/bash

# Configuration for Eureka
eureka_url="http://eureka-server:8761"
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

# Wait for MySQL to be up
echo "Waiting for MySQL..."
until nc -z user_db 3306; do
    sleep 10
done
echo "MySQL is up!"

# Now that both Eureka and MySQL are up, execute the command
echo "Executing command..."
exec $cmd
