package Compras;


/**
* Compras/CompraOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Compra.idl
* Ter�a-feira, 19 de Abril de 2016 20h59min10s BRT
*/

public interface CompraOperations 
{
  void getMelhoresOpcoesCompras (Compras.AcoesCodHolder codigosAcoes);
  void getMelhoresOpcoesComprasPorBolsa (int bolsa, Compras.AcoesCodHolder codigosAcoes);
  void getMelhoresOpcoesComprasPorCliente (int codigoCliente, Compras.AcoesCodHolder codigosAcoes);
  void shutdown ();
} // interface CompraOperations
