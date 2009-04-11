/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classificador;

/**
 *
 * @author Marianna Portela, Igor Giusti, Fábio Gomes, Gabriel Baims
 * 2009.1
 */
public class Perfil {

    private String saldo;
    private int duracao_emp;
    private String hist_credito;
    private int valor;
    private String economias;
    private String est_pessoal;
    private String classe;

    Perfil(String entrada) {
        try {
            String [] vetor = entrada.split(",");
            saldo = vetor[0];
            duracao_emp = Integer.parseInt(vetor[1]);
            hist_credito = vetor[2];
            valor = Integer.parseInt(vetor[3]);
            economias = vetor[4];
            est_pessoal = vetor[5];
            classe = vetor[6];
        } catch (Exception E) {
            System.out.println("Erro da entrada de dados.");
        }
    }

    public String getSaldo() {
        return saldo;
    }

    public int getDuracao() {
        return duracao_emp;
    }

    public String getHistorico() {
        return hist_credito;
    }

    public int getValor() {
        return valor;
    }

    public String getEconominas() {
        return economias;
    }

    public String getEstPessoal() {
        return est_pessoal;
    }

    public String getClasse() {
        return classe;
    }
    
    @Override
    public String toString ()  {
        String saida = "Saldo: " + saldo + "\n Duração do empréstimo: " + duracao_emp +
               "\n Histórico de Crédito: " + hist_credito + "\n Valor do empréstimo: " +
               valor + "\n Economias: " + economias + "\n Estado pessoal: " +
               est_pessoal + "\n Classe: " + classe + "\n";
        return saida;
    }
}
