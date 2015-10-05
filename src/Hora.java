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

		float n_millisec;
		float divisor;
		float hora, min, sec, dec;
		int horaAux, minAux, secAux, decAux;

		/*
		 * // Hora1 horaAux = Integer.parseInt(args[0]); minAux =
		 * Integer.parseInt(args[1]); secAux = Integer.parseInt(args[2]); decAux
		 * = Integer.parseInt(args[3]) * 100; if (validarHora(horaAux, minAux,
		 * secAux, decAux) == 1) { System.exit(0); } else {
		 * hora1.set(Calendar.HOUR, Integer.parseInt(args[0]));
		 * hora1.set(Calendar.MINUTE, Integer.parseInt(args[1]));
		 * hora1.set(Calendar.SECOND, Integer.parseInt(args[2]));
		 * hora1.set(Calendar.MILLISECOND, (Integer.parseInt(args[3])) * 100); }
		 * 
		 * // Hora2 horaAux = Integer.parseInt(args[4]); minAux =
		 * Integer.parseInt(args[5]); secAux = Integer.parseInt(args[6]); decAux
		 * = Integer.parseInt(args[7]) * 100; if (validarHora(horaAux, minAux,
		 * secAux, decAux) == 1) { System.exit(0); } else {
		 * hora2.set(Calendar.HOUR, Integer.parseInt(args[4]));
		 * hora2.set(Calendar.MINUTE, Integer.parseInt(args[5]));
		 * hora2.set(Calendar.SECOND, Integer.parseInt(args[6]));
		 * hora2.set(Calendar.MILLISECOND, (Integer.parseInt(args[7])) * 100); }
		 */

		//
		// Pruebas sin linea de comandos
		horaAux = 1;
		minAux = 1;
		secAux = 1;
		decAux = 1;
		if (validarHora(horaAux, minAux, secAux, decAux) == 1) {
			System.exit(0);
		} else {
			hora1.set(Calendar.HOUR, horaAux);
			hora1.set(Calendar.MINUTE, minAux);
			hora1.set(Calendar.SECOND, secAux);
			hora1.set(Calendar.MILLISECOND, decAux);
		}
		horaAux = 30;
		minAux = 1;
		secAux = 1;
		decAux = 1;
		if (validarHora(horaAux, minAux, secAux, decAux) == 1) {
			System.exit(0);
		} else {
			hora2.set(Calendar.HOUR, horaAux);
			hora2.set(Calendar.MINUTE, minAux);
			hora2.set(Calendar.SECOND, secAux);
			hora2.set(Calendar.MILLISECOND, decAux);
		} // FIN de prueba sin linea de comandos

		/*
		 * Comprobar cual es menor y "voltearlos"
		 */

		System.out.println(hora1.getTime());
		System.out.println(hora2.getTime());

		long dif_weeks = (hora2.getTimeInMillis() - hora1.getTimeInMillis());
		n_millisec = new Float(dif_weeks); // convierto en float
		divisor = (float) 3.6E6;// (1000*3600) milisec a horas
		hora = (n_millisec / divisor);
		n_millisec %= divisor;

		divisor = 60000;
		min = (float) Math.floor(n_millisec / divisor);
		n_millisec %= divisor;

		divisor = 1000;
		sec = (float) Math.floor(n_millisec / divisor);
		n_millisec %= divisor;

		divisor = 100;
		dec = n_millisec;

		System.out.printf("%.0f:%.0f:%.0f-%.0f", hora, min, sec, dec);
	}

	public static int validarHora(int hora, int min, int sec, int dec) {
		Calendar fecha = new GregorianCalendar();
		fecha.setLenient(false);

		try {
			fecha.set(Calendar.HOUR, hora);
			fecha.set(Calendar.MINUTE, min);
			fecha.set(Calendar.SECOND, sec);
			fecha.set(Calendar.MILLISECOND, dec);
			fecha.getTime();
		} catch (IllegalArgumentException excepcion) {
			System.out.println("ERROR: hora introducida incorrecta");
			return 1;
		}
		return 0;
	}
}