import cpcounter.CpCounter;
import java.util.Scanner;

/**
 * Command Point Counter
 * @author matias.kaakinen, matias.kaakinen@tuni.fi
 * Summer 2020
 *
 * Ajoluokka, joka pyörittää luo cpCounter-luokan olion ja pyörittää sen toimintoja.
 */
public class CpCounterMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hi! I keep count of your Command Points during"
                + "the battle.");
        Scanner scanner = new Scanner(System.in);
        boolean flowMain = true;
        while (flowMain) {
            System.out.println("Please, enter your starting Command Points:");
            try {
                int points = Integer.parseInt(scanner.nextLine());
                CpCounter counter = new CpCounter(points);
                System.out.println();
                System.out.println(counter.show());
                boolean flowMinor = true;
                while (flowMinor) {
                    System.out.println();
                    System.out.println("Please enter a command:");
                    String command = scanner.nextLine();
                    try {
                        switch (command.charAt(0)) {
                            case '+':
                                String[] parts1 = command.split("+");
                                int amount1 = Integer.parseInt(parts1[1]);
                                counter.increaseCp(amount1);
                                break;
                            case '-':
                                String[] parts2 = command.split("-");
                                int amount2 = Integer.parseInt(parts2[1]);
                                counter.decreaseCp(amount2);
                                break;
                            case 'f':
                                String message = counter.changeTurn("forward");
                                if (Boolean.valueOf(message)) {
                                    System.out.println();
                                    System.out.println(counter.show());
                                    break;
                                }
                                else {
                                    System.out.println(message);
                                    flowMinor = false;
                                    break;
                                }
                            case 'b':
                                counter.changeTurn("back");
                                System.out.println(counter.show());
                                break;
                            case 's':
                                System.out.println(counter.show());
                                break;
                        }
                    }
                    catch (IllegalArgumentException ie) {
                        System.out.println("Not enough Command Points!");
                    }
                    catch (Exception e) {
                        System.out.println("Wrong parameter!");
                    }
                }
                flowMain = false;

            }
            catch (Exception e) {
                System.out.println("Incorrect parameter!");
                
            }
        }
    }
}
