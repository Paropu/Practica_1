import java.util.Calendar;
import java.util.GregorianCalendar;

public class Fecha {

	public static void main(String[] args) {

		/*
		 * Introduce dos fechas por linea de comandos (dia mes anho) y devuelve:
		 * 
		 * (1) - numero de anhos (con un decimal) entre fechas
		 * 
		 * (2) - numero de dias habiles (lunes a viernes) entre fechas
		 * 
		 * (3) - numero de semanas completas (lunes a domingo) entre fechas
		 * 
		 */

		float n_years = 0; // Resultado 1
		int n_days = 0; // Resultado 2
		float n_weeks = 0; // Resultado 3
		int dia1, mes1, anho1, dia2, mes2, anho2;

		// Comprobamos el numero de parametros introducidos
		if (args.length < 5) {
			System.out.println("No has introducido suficientes datos");
			System.exit(0);
		}

		// Recogemos fecha1 de linea de parametros
		dia1 = Integer.parseInt(args[0]);
		mes1 = Integer.parseInt(args[1]);
		anho1 = Integer.parseInt(args[2]);
		if (validarFecha(dia1, mes1 - 1, anho1) == true) { // Validacion
			System.exit(0);
		}
		Calendar fecha1 = new GregorianCalendar(anho1, mes1 - 1, dia1);

		// Recogemos fecha2 de linea de parametros
		dia2 = Integer.parseInt(args[3]);
		mes2 = Integer.parseInt(args[4]);
		anho2 = Integer.parseInt(args[5]);
		if (validarFecha(dia2, mes2 - 1, anho2) == true) { // Validacion
			System.exit(0);
		}
		Calendar fecha2 = new GregorianCalendar(anho2, mes2 - 1, dia2);

		// Compruebo si fecha1 posterior a fecha2
		if (fecha1.compareTo(fecha2) > 0) {
			Calendar fechaAux = new GregorianCalendar();
			fechaAux = fecha1;
			fecha1 = fecha2;
			fecha2 = fechaAux;
		} // fecha1 > fecha2 siempre

		// 1 - Anhos
		while (anho1 < anho2) {
			n_years++;
			anho1++;
		}

		int dianho1 = fecha1.get(Calendar.DAY_OF_YEAR);
		int dianho2 = fecha2.get(Calendar.DAY_OF_YEAR);
		int dif_dias = dianho2 - dianho1;
		if (dif_dias < 0) {
			n_years--;
			dif_dias += 365;
		}
		n_years += (float) dif_dias / 365;

		// 2 - N�mero de dias habiles
		int anhoAux = fecha1.get(Calendar.YEAR); // Guardo variables
		int mesAux = fecha1.get(Calendar.MONTH);
		int diaAux = fecha1.get(Calendar.DAY_OF_MONTH);
		for (;; fecha1.add(Calendar.DAY_OF_MONTH, 1)) {
			if (fecha1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || fecha1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				if (fecha1.compareTo(fecha2) == 0) {
					break;
				}
				continue;
			}
			if (fecha1.compareTo(fecha2) == 0) {
				n_days++;
				break;
			}
			n_days++;
		}
		fecha1.set(anhoAux, mesAux, diaAux); // Recupero la fecha original

		// 3 - N�mero de semanas completas
		// Paso la primera fecha al primer lunes posterior
		while (fecha1.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			fecha1.add(Calendar.DAY_OF_MONTH, 1);
		}
		// Compruebo si la primera fecha sigue siendo anterior
		if (fecha1.compareTo(fecha2) > 0) {
			n_weeks = 0;
		} else {
			// Hallo la diferencia de tiempo en milisegundos
			long dif_weeks = (fecha2.getTimeInMillis() - fecha1.getTimeInMillis());
			n_weeks = new Float(dif_weeks);
			n_weeks /= 6.048E8; // =(1000*3600*24*7) miliseg a semanas
		}

		// Resultados
		System.out.println("Entre las dos fechas hay: ");
		System.out.printf("%.1f Anhos\n", n_years);
		System.out.println(n_days + " Dias habiles");
		System.out.printf("%.0f Semanas completas", n_weeks);
		System.exit(0);
	}

	public static boolean validarFecha(int dia, int mes, int anho) {
		Calendar fecha = new GregorianCalendar(anho, mes, dia);
		fecha.setLenient(false);
		try {
			fecha.getTime();
		} catch (IllegalArgumentException excepcion) {
			System.out.println("ERROR: fecha introducida incorrecta");
			return true;
		}
		return false;
	}
}