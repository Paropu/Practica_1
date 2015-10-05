import java.util.Calendar;
import java.util.GregorianCalendar;

public class Hora {

	public static void main(String[] args) {

		/*
		 * Introduce dos instantes temporales por linea de comandos (horas
		 * minutos segundos y decimas) y devuelve:
		 * 
		 * Tiempo transcurrido entre las dos fechas en formato hh:mm:ss-d
		 * 
		 * ¿Las horas siempre pertenecen al mismo dia?
		 */

		Calendar hora1 = new GregorianCalendar();
		Calendar hora2 = new GregorianCalendar();

		/*
		 * hora1.set(Calendar.HOUR, Integer.parseInt(args[0]));
		 * hora1.set(Calendar.MINUTE, Integer.parseInt(args[1]));
		 * hora1.set(Calendar.SECOND, Integer.parseInt(args[2]));
		 * hora1.set(Calendar.MILLISECOND, (Integer.parseInt(args[3])) * 100);
		 * 
		 * // FALTA validar fecha
		 * 
		 * hora2.set(Calendar.HOUR, Integer.parseInt(args[4]));
		 * hora2.set(Calendar.MINUTE, Integer.parseInt(args[5]));
		 * hora2.set(Calendar.SECOND, Integer.parseInt(args[6]));
		 * hora2.set(Calendar.MILLISECOND, (Integer.parseInt(args[7])) * 100);
		 * // FALTA validar fecha
		 */
		hora1.set(Calendar.HOUR, 1);
		hora1.set(Calendar.MINUTE, 8);
		hora1.set(Calendar.SECOND, 15);
		hora1.set(Calendar.MILLISECOND, 6);
		hora2.set(Calendar.HOUR, 16);
		hora2.set(Calendar.MINUTE, 32);
		hora2.set(Calendar.SECOND, 42);
		hora2.set(Calendar.MILLISECOND, 1);

		/*
		 * Comprobar cual es menor y "voltearlos"
		 */

		System.out.println(hora1.getTime());
		System.out.println(hora2.getTime());

	}
}