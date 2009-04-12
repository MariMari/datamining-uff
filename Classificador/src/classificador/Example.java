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
            String [] attributes = lineRead.split(",");
            
            duracao_emp = Integer.parseInt(attributes[1]);
            valor = Integer.parseInt(attributes[3]);
            
            saldo = attributes[0];
            hist_credito = attributes[2];
            economias = attributes[4];
            est_pessoal = attributes[5];
            classe = attributes[6];
    }
    
    public int getDuracao() {
        return duracao_emp;
    }
    
    public int getValor() {
        return valor;
    }

    public String getSaldo() {
        return saldo;
    }

    public String getHistorico() {
        return hist_credito;
    }

    public String getEconomias() {
        return economias;
    }

    public String getEstPessoal() {
        return est_pessoal;
    }

    public String getClasse() {
        return classe;
    }
    
    public void setDuracao(int duracao) {
        duracao_emp = duracao;
    }
    
    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public void setHistorico(String historico) {
        hist_credito = historico;
    }

    public void setEconomias(String economias) {
        this.economias = economias;
    }

    public void setEstPessoal(String estPessoal) {
        this.est_pessoal = estPessoal;
    }

    public void setClasse(String classe) {
        this.classe = classe;
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
