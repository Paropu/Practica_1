
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Fecha {

	public static void main(String[] args) {

		/*
		 * Introduce dos fechas por linea de comandos y devuelve:
		 * 
		 * (1) - numero de años (con un decimal) entre fechas
		 * 
		 * (2) - numero de dias hábiles (lunes a viernes) entre fechas
		 * 
		 * (3) - numero de semanas completas (lunes a domingo) entre fechas
		 * 
		 */

		Calendar fecha1 = new GregorianCalendar();
		Calendar fecha2 = new GregorianCalendar();

		float n_years = 0; // Resultado 1
		int n_days = 0; // Resultado 2
		float n_weeks = 0; // Resultado 3

		int diaAux, mesAux, anhoAux;
		int diaSemana;

		// Comprobamos el numero de parametros introducidos
		// if (args.length < 5) {
		// System.out.println("No has introducido suficientes datos");
		// System.exit(0);
		// }

		/*
		 * // Recogemos fecha1 de linea de parametros diaAux = Integer.parseInt(args[0]); mesAux = Integer.parseInt(args[1]); if (mesAux == 1) { mesAux = 11; } else { mesAux--; } anhoAux = Integer.parseInt(args[2]); if (validarFecha(diaAux, mesAux, anhoAux) == 1) { // Validacion System.exit(0); } fecha1.set(Calendar.DAY_OF_MONTH, diaAux); // Guardamos si es correcta fecha1.set(Calendar.MONTH, mesAux); fecha1.set(Calendar.YEAR, anhoAux);
		 * 
		 * // Recogemos fecha2 de linea de parametros diaAux = Integer.parseInt(args[3]); mesAux = Integer.parseInt(args[4]); if (mesAux == 1) { mesAux = 11; } else { mesAux--; } anhoAux = Integer.parseInt(args[5]); if (validarFecha(diaAux, mesAux, anhoAux) == 1) { // Validacion System.exit(0); } fecha2.set(Calendar.DAY_OF_MONTH, diaAux); // Guardamos si es correcta fecha2.set(Calendar.MONTH, mesAux); fecha2.set(Calendar.YEAR, anhoAux);
		 */

		// Pruebas sin linea de comandos
		diaAux = 14;
		mesAux = 0;
		anhoAux = 2015;

		if (validarFecha(diaAux, mesAux, anhoAux) == 1) {
			System.exit(0);
		}
		fecha1.set(Calendar.DAY_OF_MONTH, diaAux);
		fecha1.set(Calendar.MONTH, mesAux);
		fecha1.set(Calendar.YEAR, anhoAux);

		diaAux = 20;
		mesAux = 5;
		anhoAux = 2015;
		if (validarFecha(diaAux, mesAux, anhoAux) == 1) {
			System.exit(0);
		}
		fecha2.set(Calendar.DAY_OF_MONTH, diaAux);
		fecha2.set(Calendar.MONTH, mesAux);
		fecha2.set(Calendar.YEAR, anhoAux);
		// FIN de prueba sin linea de comandos

		// Compruebo si fecha1 posterior a fecha2
		int ordenFechas = fecha1.compareTo(fecha2);
		if (ordenFechas > 0) {
			Calendar fechaAux = new GregorianCalendar();
			fechaAux = fecha1;
			fecha1 = fecha2;
			fecha2 = fechaAux;
		} // fecha1 > fecha2 siempre

		// Muestro fechas por pantalla
		// System.out.println(fecha1.getTime());
		// System.out.println(fecha2.getTime());

		// Compruebo si las fechas coinciden
		if (fecha1.equals(fecha2)) {
			n_years = 0;
			n_weeks = 0;
			diaSemana = fecha1.get(Calendar.DAY_OF_WEEK);
			if (!(diaSemana == Calendar.SATURDAY || diaSemana == Calendar.SUNDAY))
				n_days = 1;

			// Resultado
			System.out.println("Entre las dos fechas hay: ");
			System.out.println(n_years + " Años");
			System.out.println(n_days + " Dias hábiles");
			System.out.println(n_weeks + " Semanas completas");
			System.exit(0);
		}

		// 1 - Años
		long dif_years = (fecha2.getTimeInMillis() - fecha1.getTimeInMillis());
		float divisor = (float) 3.1536E10; // =(1000*3600*24*365) miliseg a años
		n_years = new Float(dif_years); // paso milisegundos a float
		n_years /= divisor;

		// 2 - Número de dias habiles
		int i; // dias de diferencia entre fechas para no perder fecha original
		for (i = 0;; i++) {
			fecha1.add(Calendar.DAY_OF_MONTH, i);
			diaSemana = fecha1.get(Calendar.DAY_OF_WEEK);
			if (diaSemana == Calendar.SATURDAY || diaSemana == Calendar.SUNDAY) {
				if (fecha1.compareTo(fecha2) == 0) {
					break;
				}
				fecha1.add(Calendar.DAY_OF_MONTH, -i);
				continue;
			}
			if (fecha1.compareTo(fecha2) == 0) {
				n_days++;
				break;
			}
			n_days++;
			fecha1.add(Calendar.DAY_OF_MONTH, -i);
		}
		fecha1.add(Calendar.DAY_OF_MONTH, -i);

		// 3 - Número de semanas completas
		// Paso la primera fecha al primer lunes posterior
		diaSemana = fecha1.get(Calendar.DAY_OF_WEEK);
		while (diaSemana != Calendar.MONDAY) {
			fecha1.add(Calendar.DAY_OF_MONTH, 1);
			diaSemana = fecha1.get(Calendar.DAY_OF_WEEK);
		}
		// Compruebo si la primera fecha sigue siendo anterior
		if (fecha1.compareTo(fecha2) > 0) {
			n_weeks = 0;
		} else {
			// Hallo la diferencia de tiempo en milisegundos
			long dif_weeks = (fecha2.getTimeInMillis() - fecha1.getTimeInMillis());
			n_weeks = new Float(dif_weeks); // convierto en float
			divisor = (float) 6.048E8; // =(1000*3600*24*7) miliseg a semanas
			n_weeks /= divisor; // Paso resultado de milisegundos a semanas
		}
		// Resultados
		System.out.println("Entre las dos fechas hay: ");
		System.out.printf("%.1f Años\n", n_years);
		System.out.println(n_days + " Dias hábiles");
		System.out.printf("%.0f Semanas completas", n_weeks);
		System.exit(0);
	}

	public static int validarFecha(int dia, int mes, int anho) {
		Calendar fecha = new GregorianCalendar();
		fecha.setLenient(false);

		try {
			fecha.set(Calendar.DAY_OF_MONTH, dia);
			fecha.set(Calendar.MONTH, mes);
			fecha.set(Calendar.YEAR, anho);
			fecha.getTime();
		} catch (IllegalArgumentException excepcion) {
			System.out.println("ERROR: fecha introducida incorrecta");
			return 1;
		}
		return 0;
	}
}
