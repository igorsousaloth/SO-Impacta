package ContaBancária;

public class Conta {
	private long saldo;
	private String dono;
	public Conta(long saldoInicial, String dono) {
		saldo = saldoInicial;
		this.dono=dono;
		imprimir("saldo inicial de R$: " + saldo);
	}
	
	public void depositar(long deposito){
		this.saldo+= deposito;
		imprimir("Depósito de: R$ " + deposito + "; - saldo atualizado: R$ "+ saldo);		
	}
	
	public boolean sacar(long saque) {
		if(saque <= saldo){
			Espera.umSegundo(); //simula uma demora na operação de débito
			this.saldo-= saque;
			imprimir("Saque bem-sucedido de: R$ " + saque + "; - saldo atualizado: R$ " + saldo);
			return true;
		}else{
			imprimir("Saque não permitido de: R$ " + saque + "; - saldo atualizado: R$ " + saldo);
			return false;
		}
	}
	private void imprimir(String msg){
		System.out.println("Conta de: " + dono + " | " + msg);
	}
}
