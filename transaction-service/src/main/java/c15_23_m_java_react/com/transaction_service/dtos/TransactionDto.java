
package c15_23_m_java_react.com.transaction_service.dtos;



public class TransactionDto {

    private ProductDto productoDto;

    private UserDto userDto;

    public ProductDto getProductoDto() {
        return this.productoDto;
    }

    public void setProductoDto(ProductDto productoDto) {
        this.productoDto = productoDto;
    }

	public UserDto getuserDto() {
        return this.userDto;
    }

    public void setProductoDto(UserDto userDto) {
        this.userDto = userDto;
    }
}



//public record TransactionDtoEnviado(Long id,
//								Long usuario_id,
//								Producto producto,
//								montoTotal,
//								impuestos,
//								montoDescontado,
//								fecha
//								){
//	public TransactionDtoEnviado(TransactionEntity transaction){
//		this(transaction.getId(),
//			transaction.getUsruaio_id(),
//			transaction.getProducto(),
//			transaction.getMontoTotal(),
//			transaction.getImpuestos(),
//			transaction.getMontoDescontado(),
//			transaction.getFecha()
//		);
//	}
//
//}
//
//public record TransactionDtoRecibido(@NotBlank Long id,
//							@NotBlank Long usuario_id,
//							@NotBlank Producto producto,
//							@NotBlank montoTotal,
//							@NotBlank impuestos,
//							@NotBlank montoDescontado,
//							@Notblank fecha
//							){
//}