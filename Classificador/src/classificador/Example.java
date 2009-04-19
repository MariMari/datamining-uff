/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classificador;

import static classificador.Utils.*;

/**
 *
 * @author Marianna Portela
 * @author igorg
 */
public class Example {
    
    //Constantes referentes aos valores dos dominios dos atributos discretos.
    private static final String[] dominioSaldo = 
            {"<0", "0<=X<200", ">=200", "'no checking'"};
    
    private static final String[] dominioHistCredito = 
            {"'no credits/all paid'", "'all paid'", "'existing paid'",
             "'delayed previously'", "'critical/other existing credit'"};
    
    private static final String[] dominioEconomias =
            {"<100", "100<=X<500", "500<=X<1000", ">=1000", "'no known savings'"};
    
    private static final String[] dominioEstPessoal =
            {"'male div/sep'", "'female div/dep/mar'", "'male single'",
             "'male mar/wid'", "'female single'"};
    
    private static final String[] dominioClasse =
            {"good", "bad"};

    private double valor;
    private int duracao_emp;
    private int saldo;
    private int hist_credito;
    private int economias;
    private int est_pessoal;
    private int classe;

    Example(String lineRead) throws IllegalArgumentException {
            String [] attributes = lineRead.split(",");
            
            setValor(Double.parseDouble(attributes[3]));
            
            setDuracao(Integer.parseInt(attributes[1]));
            try {
                setSaldo(attrStringToNumerical(dominioSaldo, attributes[0]));
                setHistorico(attrStringToNumerical(dominioHistCredito,attributes[2]));
                setEconomias(attrStringToNumerical(dominioEconomias, attributes[4]));
                setEstPessoal(attrStringToNumerical(dominioEstPessoal, attributes[5]));
                setClasse(attrStringToNumerical(dominioClasse, attributes[6]));
            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage());
            }
    }
    
    public int getDuracao() {
        return duracao_emp;
    }
    
    public double getValor() {
        return valor;
    }

    public int getSaldo() {
        return saldo;
    }

    public int getHistorico() {
        return hist_credito;
    }

    public int getEconomias() {
        return economias;
    }

    public int getEstPessoal() {
        return est_pessoal;
    }

    public int getClasse() {
        return classe;
    }
    
    public void setDuracao(int duracao) {
        duracao_emp = duracao;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public void setHistorico(int historico) {
        hist_credito = historico;
    }

    public void setEconomias(int economias) {
        this.economias = economias;
    }

    public void setEstPessoal(int estPessoal) {
        this.est_pessoal = estPessoal;
    }

    public void setClasse(int classe) {
        this.classe = classe;
    }
    
    /**
     * Retorna a representacao do exemplo em String. O formato e o mesmo de um
     * registro em uma base de dados.
     * 
     * @return a representacao do exemplo em formato de registro 
     */
    public String toRegisterString() {
        String registerExample = attrNumericalToString(dominioSaldo, saldo)
                     + "," + duracao_emp
                     + "," + attrNumericalToString(dominioHistCredito, hist_credito)
                     + "," + valor
                     + "," + attrNumericalToString(dominioEconomias, economias)
                     + "," + attrNumericalToString(dominioEstPessoal, est_pessoal)
                     + "," + attrNumericalToString(dominioClasse, classe);
        return null;
    }
    
    @Override
    public String toString () {
        String example = "Saldo: " + attrNumericalToString(dominioSaldo, saldo)
                     + "\nDuração do empréstimo: " + duracao_emp
                     + "\nHistórico de Crédito: " + attrNumericalToString(dominioHistCredito, hist_credito)
                     + "\nValor do empréstimo: " + valor
                     + "\nEconomias: " + attrNumericalToString(dominioEconomias, economias)
                     + "\nEstado pessoal: " + attrNumericalToString(dominioEstPessoal, est_pessoal)
                     + "\nClasse: " + attrNumericalToString(dominioClasse, classe);
        return example;
    }
}