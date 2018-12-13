package AlerteErreur;


public class Reguliere extends Erreurs{

    public Reguliere(){ // //

        setContentText("Le détermiant de votre matrice ne doit pas donner 0." +
                " /Évitez de créer des lignes ou des colonnes étant le multiple d'autres lignes ou colonnes.");
        setHeaderText("Attention !! Votre matrice n'est pas régulière.");
    }
}
