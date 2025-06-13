package model.algorithm;

import experience.Sonde;
import model.Data;
import model.algorithm.HeapSort;
import view.Visualizer;

public class Main {
    public static void main(String[] args) {
        // Définition du tableau avec des valeurs personnalisées
        int[] tableau = {4,2,8,1,9,6,7,3,5,10}; // Vous pouvez modifier ces valeurs
        
        // Création de l'objet Data avec le tableau défini
        Data data = new Data(tableau);
        
        // Afficher le tableau non trié
        System.out.println("Tableau non trié :");
        System.out.println(data);
        
        // Création de l'algorithme de tri
        HeapSort heapSort = new HeapSort();
        
        // Tri du tableau (sans visualiseur)
        Sonde resultat = heapSort.sort(data, null);
        
        // Afficher le tableau trié
        System.out.println("\nTableau trié :");
        System.out.println(data);
        
        // Afficher les informations détaillées de la sonde
        System.out.println("\nInformations détaillées de la sonde :");
        System.out.println("Nombre de comparaisons : " + resultat.getNbComparaisons());
        System.out.println("Nombre d'accès au tableau : " + resultat.getNbAccess());
        System.out.println("Nombre d'échanges : " + resultat.getNbEchanges());
        System.out.println("Temps d'exécution (ms) : " + resultat.getTime());
        
        // Afficher la représentation complète de la sonde
        System.out.println("\nReprésentation complète de la sonde :");
        System.out.println(resultat.toString());
    }
}