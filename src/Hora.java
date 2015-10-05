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

		GregorianCalendar hora1 = new GregorianCalendar();
		GregorianCalendar hora2 = new GregorianCalendar();

		float n_millisec;
		float divisor;
		float hora, min, sec, dec;

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
		hora1.set(Calendar.MINUTE, 1);
		hora1.set(Calendar.SECOND, 1);
		hora1.set(Calendar.MILLISECOND, 1);
		hora2.set(Calendar.HOUR, 5);
		hora2.set(Calendar.MINUTE, 1);
		hora2.set(Calendar.SECOND, 3);
		hora2.set(Calendar.MILLISECOND, 1);

		/*
		 * Comprobar cual es menor y "voltearlos"
		 */

		System.out.println(hora1.getTime());
		System.out.println(hora2.getTime());

		long dif_weeks = (hora2.getTimeInMillis() - hora1.getTimeInMillis());
		n_millisec = new Float(dif_weeks); // convierto en float
		System.out.println(n_millisec);
		divisor = (float) 3.6E6;// (1000*3600) milisec a horas
		hora = (float) Math.floor(n_millisec / divisor);
		n_millisec %= divisor;
		System.out.println(n_millisec);

		divisor = 3600;
		min = (float) Math.floor(n_millisec / divisor);
		n_millisec %= divisor;

		divisor = 1000;
		sec = (float) Math.floor(n_millisec / divisor);
		n_millisec %= divisor;

		divisor = 100;
		dec = n_millisec;

		System.out.printf("%.0f:%.0f:%.0f-%.0f", hora, min, sec, dec);

		// Calculamos horas

	}

	public static int validarHora(int hora, int min, int sec, int dec) {
		return 0;
	}
}