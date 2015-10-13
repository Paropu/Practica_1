import java.util.Calendar;
import java.util.GregorianCalendar;

public class Hora {

	public static void main(String[] args) {

		/*
		 * Introduce dos instantes temporales por linea de comandos (horas minutos segundos y decimas) y devuelve:
		 * 
		 * Tiempo transcurrido entre las dos fechas en formato hh:mm:ss-d
		 * 
		 */

		Calendar hora1 = new GregorianCalendar();
		Calendar hora2 = new GregorianCalendar();

		float n_millisec; // Resultado en milisegundos
		float divisor;
		float hora, min, sec, dec; // Resultados

		if (args.length < 7) {
			System.out.println("No has introducido suficientes datos");
			System.exit(0);
		}

		// Hora1
		if (validarHora(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]),
				Integer.parseInt(args[3]) * 100) == true) {
			System.exit(0);
		} else {
			hora1.set(Calendar.HOUR, Integer.parseInt(args[0]));
			hora1.set(Calendar.MINUTE, Integer.parseInt(args[1]));
			hora1.set(Calendar.SECOND, Integer.parseInt(args[2]));
			hora1.set(Calendar.MILLISECOND, (Integer.parseInt(args[3])) * 100);
		}

		// Hora2
		if (validarHora(Integer.parseInt(args[4]), Integer.parseInt(args[5]), Integer.parseInt(args[6]),
				Integer.parseInt(args[7]) * 100) == true) {
			System.exit(0);
		} else {
			hora2.set(Calendar.HOUR, Integer.parseInt(args[4]));
			hora2.set(Calendar.MINUTE, Integer.parseInt(args[5]));
			hora2.set(Calendar.SECOND, Integer.parseInt(args[6]));
			hora2.set(Calendar.MILLISECOND, (Integer.parseInt(args[7])) * 100);
		}

		// Intercambio si hora1 posterior a hora2
		if (hora1.compareTo(hora2) > 0) {
			Calendar fechaAux = new GregorianCalendar();
			fechaAux = hora1;
			hora1 = hora2;
			hora2 = fechaAux;
		}

		// Calculo tiempo en milisegundos entre las dos
		long dif_weeks = (hora2.getTimeInMillis() - hora1.getTimeInMillis());
		n_millisec = new Float(dif_weeks); // convierto en float

		divisor = (float) 3.6E6;// (1000*3600) milisec a horas
		hora = (float) Math.floor(n_millisec / divisor);
		n_millisec %= divisor; // Resto

		divisor = 60000; // millisec a minutos
		min = (float) Math.floor(n_millisec / divisor);
		n_millisec %= divisor; // Resto

		divisor = 1000; // millisec a segundos
		sec = (float) Math.floor(n_millisec / divisor);
		n_millisec %= divisor; // Resto

		divisor = 100;
		dec = (float) Math.floor(n_millisec / divisor);

		// Resultado formateado
		if (hora < 10) {
			System.out.printf("0%.0f:", hora);
		} else {
			System.out.printf("%.0f:", hora);
		}
		if (min < 10) {
			System.out.printf("0%.0f:", min);
		} else {
			System.out.printf("%.0f:", min);
		}
		if (sec < 10) {
			System.out.printf("0%.0f-", sec, dec);
		} else {
			System.out.printf("%.0f-%.0f", sec, dec);
		}
	}

	public static boolean validarHora(int hora, int min, int sec, int dec) {
		Calendar fecha = new GregorianCalendar();
		fecha.setLenient(false);
		fecha.set(Calendar.HOUR, hora);
		fecha.set(Calendar.MINUTE, min);
		fecha.set(Calendar.SECOND, sec);
		fecha.set(Calendar.MILLISECOND, dec);

		try {
			fecha.getTime();
		} catch (IllegalArgumentException excepcion) {
			System.out.println("ERROR: hora introducida incorrecta");
			return true;
		}
		return false;
	}
}