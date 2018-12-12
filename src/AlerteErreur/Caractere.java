package AlerteErreur;

public class Caractere extends Erreurs {

    public String getLettre() {
        return lettre;
    }

    public void setLettre(String lettre) {
        this.lettre = lettre;
    }

    private String lettre = "";  //trouver moyen lettre

    public Caractere(){

        setContentText("Vous devez rentrer des nombres entiers.");
        setHeaderText("Attention!! Le contenu de la matrice " + lettre + " n'a pas le bon format.");
    }
}
