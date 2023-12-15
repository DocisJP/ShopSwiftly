package c15_23_m_java_react.com.transaction_service.repository;

import c15_23_m_java_react.com.transaction_service.entitys.TransactionEntity;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

TransactionEntity findByUsuario_id(Long usuario_id);

@Query("SELECT t FROM TransactionEntity t ")
List<TransactionEntity> listarTransacciones();

@Query("SELECT t FROM TransactionEntity t WHERE t.montoTotal BETWEEN :montoTotalMin AND :montoTotalMax")
List<TransactionEntity> buscarPorMontoTotal(@Param("montoTotalMin") Double montoTotalMin, @Param("montoTotalMax") Double montoTotalMax);

@Query("SELECT t FROM TransactionEntity t WHERE t.montoTotal = :montoTotal AND t.estado = true")
List<TransactionEntity> buscarPorMontoTotalyEstado(@Param("montoTotal") Double montoTotal);

@Modifying
@Query("UPDATE TransactionEntity t SET t.montoTotal = :montoTotal WHERE t.id = :id")
void modificarMontoTotal(@Param("montoTotal") Double montoTotal, @Param("id") Long id);

@Modifying
@Query("UPDATE TransactionEntity t SET t.impuestos = :impuestos WHERE t.id = :id")
void modificarImpuestos(@Param("impuestos") Double impuestos, @Param("id") Long id);

@Modifying
@Query("UPDATE TransactionEntity t SET t.montoDescontado = :montoDescontado WHERE t.id = :id")
void modificarMontoDescontado(@Param("montoDescontado") Double montoDescontado, @Param("id") Long id);

@Modifying
@Query("UPDATE TransactionEntity t SET t.estado = true WHERE t.id = :id AND t.estado = false")
void cambiarEstadoaTrue(@Param("id") Long id);

@Modifying
@Query("UPDATE TransactionEntity t SET t.estado = false WHERE t.id = :id AND t.estado = true")
void cambiarEstadoaFalse(@Param("id") Long id);

@Modifying
@Query("UPDATE TransactionEntity t SET t.usuario_id = :usuario_id , t.montoTotal = :montoTotal, t.impuestos = :impuestos," + "t.montoDescontado = :montoDescontado, t.fecha = :fecha, " + "t.estado = :estado WHERE t.id = :id")
void modificarTransaccion(@Param("usuario_id") Long usuario_id,
	@Param("montoTotal") Double montoTotal,
	@Param("impuestos") Double impuestos,
	@Param("montoDescontado") Double montoDescontado,
	@Param("fecha") Date fecha,
	@Param("estado") Boolean estado,
	@Param("id") Long id);

}
