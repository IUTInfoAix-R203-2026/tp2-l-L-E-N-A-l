package fr.univ_amu.iut.exercice3;

/**
 * Exercice 3 - Convertisseur de chiffres romains en nombres arabes.
 *
 * <p>Règles :
 *
 * <ul>
 *   <li>Les symboles valides sont {@code I=1, V=5, X=10, L=50, C=100, D=500, M=1000}
 *   <li>Lus de gauche à droite, les symboles s'additionnent : {@code VI = 5 + 1 = 6}
 *   <li>Un symbole placé avant un symbole de valeur supérieure se soustrait : {@code IV = 5 - 1 =
 *       4}
 *   <li>Les seules soustractions valides sont :
 *       <ul>
 *         <li>I avant V ou X ({@code IV = 4}, {@code IX = 9})
 *         <li>X avant L ou C ({@code XL = 40}, {@code XC = 90})
 *         <li>C avant D ou M ({@code CD = 400}, {@code CM = 900})
 *       </ul>
 *       Toute autre soustraction doit lever {@link IllegalArgumentException}.
 * </ul>
 *
 * <p>Conseils TDD : commencez par gérer uniquement {@code I}, puis {@code II} / {@code III} (fake
 * it), puis {@code V} (triangulation), puis {@code VI} (addition de deux symboles différents), puis
 * {@code IV} (introduction de la soustraction). À ce moment-là, <b>extrayez une méthode</b> pour
 * calculer la valeur d'un symbole - vous en aurez besoin pour les symboles suivants.
 */
public class ConvertisseurDeNombreRomain {

  /**
   * Convertit une chaîne de chiffres romains en valeur entière.
   *
   * @param chiffreRomain chaîne composée de symboles romains (par exemple {@code "XLIX"})
   * @return la valeur entière correspondante
   * @throws IllegalArgumentException si la chaîne contient un symbole invalide ou une soustraction
   *     interdite
   */
  public int valeurDe(char car) {

    switch (car) {
      case 'I':
        return 1;

      case 'V':
        return 5;

      case 'X':
        return 10;

      case 'L':
        return 50;

      case 'C':
        return 100;

      case 'D':
        return 500;

      case 'M':
        return 1000;

      default:
        throw new IllegalArgumentException();
    }
  }

  private boolean isSubstraction(char current, char pred) {

    return valeurDe(pred) < valeurDe(current);
  }

  private boolean isValidSubstraction(char current, char pred) {
    return current == 'V' && pred == 'I'
        || current == 'X' && pred == 'I'
        || current == 'L' && pred == 'X'
        || current == 'C' && pred == 'X'
        || current == 'D' && pred == 'C'
        || current == 'M' && pred == 'C';
  }

  public int enNombreArabe(String chiffreRomain) {

    int total = valeurDe(chiffreRomain.charAt(0));

    for (int i = 1; i < chiffreRomain.length(); i++) {

      char current = chiffreRomain.charAt(i);
      char pred = chiffreRomain.charAt(i - 1);

      if (isSubstraction(current, pred)) {

        if (isValidSubstraction(current, pred)) {

          total -= 2 * valeurDe(pred);

        } else throw new IllegalArgumentException();
      }

      total += valeurDe(current);
    }

    return total;
  }
}
