/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classificador;

/**
 *
 * @author Marianna Portela
 * @author igorg
 */
public class Example {

    private int valor;
    private int duracao_emp;
    private String saldo;
    private String hist_credito;
    private String economias;
    private String est_pessoal;
    private String classe;

    Example(String lineRead) {
        try {
            String [] attributes = lineRead.split(",");
            
            duracao_emp = Integer.parseInt(attributes[1]);
            valor = Integer.parseInt(attributes[3]);
            
            saldo = attributes[0];
            hist_credito = attributes[2];
            economias = attributes[4];
            est_pessoal = attributes[5];
            classe = attributes[6];
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
        String out = "Saldo: " + saldo
                     + "\n Duração do empréstimo: " + duracao_emp
                     + "\n Histórico de Crédito: " + hist_credito
                     + "\n Valor do empréstimo: " + valor
                     + "\n Economias: " + economias
                     + "\n Estado pessoal: " + est_pessoal
                     + "\n Classe: " + classe;
        return out;
    }
}
