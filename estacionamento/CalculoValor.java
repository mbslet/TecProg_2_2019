
public class CalculoValor {

	 private Acesso source;
	 
	 int quantidadeHoras, quantidadeMinutos;
	 float valorTotal = 0, valorHoras, valorFracoes;
	 
	 public CalculoValor(Acesso source) {
	  this.source = source;
	 }
	 
	 public float computar() {
	  quantidadeHoras = qtHoras(); 
	    
	  if (source.horaSaida == source.horaEntrada)
	   quantidadeMinutos = qtMinutos();
	  else if (compareHoras() && source.minutosEntrada == source.minutosSaida){
	   quantidadeMinutos = 0;
	   quantidadeHoras = qtHoras();
	  }
	  else if (compareHoras() && source.minutosEntrada > source.minutosSaida) 
	   quantidadeMinutos = qtMinutos();
	  else if (compareHoras() && source.minutosSaida < source.minutosEntrada){
	   quantidadeMinutos = source.minutosSaida + (60 - source.minutosEntrada);
	   quantidadeHoras = qtHoras() - 1;
	  }
	  else {
	   quantidadeHoras = 0;
	   quantidadeMinutos = 0;
	  }
	 
	  valorHoras = valorTotal + quantidadeHoras * source.VALOR_HORA;
	  valorFracoes = (float) (valorHoras + Math.ceil(quantidadeMinutos / 15.0) * source.VALOR_FRACAO);  
	  
	  if (quantidadeHoras >=9)
	   return source.VALOR_DIARIA;
	  else 
	   return valorFracoes;
	 }

	private boolean compareHoras() {
		return source.horaSaida > source.horaEntrada;
	}

	private int qtMinutos() {
		return source.minutosSaida - source.minutosEntrada;
	}

	private int qtHoras() {
		return source.horaSaida - source.horaEntrada;
	}
	}