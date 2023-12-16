
package c15_23_m_java_react.com.transaction_service.entitys;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "transactions", schema = "transactions_db")
public class TransactionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long user_id;

	private String userUsername;

	private String userEmail;

	private String productName;

	private Double productPrice;
	
	private Double productDiscount;

	private Long productId;

	private Double montoTotal;
	
	private Double impuestos;
	
	private Double montoDescontado;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	private Boolean estado;




	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser_id() {
		return this.user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUserUsername() {
		return this.userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Double getProductDiscount() {
		return this.productDiscount;
	}

	public void setProductDiscount(Double productDiscount) {
		this.productDiscount = productDiscount;
	}

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Double getMontoTotal() {
		return this.montoTotal;
	}

	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public Double getImpuestos() {
		return this.impuestos;
	}

	public void setImpuestos(Double impuestos) {
		this.impuestos = impuestos;
	}

	public Double getMontoDescontado() {
		return this.montoDescontado;
	}

	public void setMontoDescontado(Double montoDescontado) {
		this.montoDescontado = montoDescontado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Boolean isEstado() {
		return this.estado;
	}

	public Boolean getEstado() {
		return this.estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	
}
