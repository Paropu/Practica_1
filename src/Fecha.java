
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
		 * Validar las fechas antes de empezar
		 */

		// Hay que restar 1 numero al mes!!

		// excepciones: en modo LENIN genera una excepcion

		Calendar fecha1 = new GregorianCalendar();
		Calendar fecha2 = new GregorianCalendar();

		float n_years = 0; // Resultado 1
		int n_days = 0; // Resultado 2
		float n_weeks = 0; // Resultado 3

		// Comprobamos el numero de parametros introducidos
		// if (args.length < 5) {
		// System.out.println("No has introducido suficientes datos");
		// System.exit(0);
		// }

		/*
		 * fecha1.set(Calendar.MONTH, Integer.parseInt(args[1]));
		 * fecha1.add(Calendar.MONTH, -1);
		 * 
		 * fecha1.set(Calendar.DAY_OF_MONTH, Integer.parseInt(args[0]));
		 * fecha1.set(Calendar.YEAR,Integer.parseInt(args[2]));
		 * 
		 * //FALTA validar fecha
		 * 
		 * fecha2.set(Calendar.MONTH,Integer.parseInt(args[4]));
		 * fecha2.add(Calendar.MONTH, -1);
		 * 
		 * fecha2.set(Calendar.DAY_OF_MONTH, Integer.parseInt(args[3]));
		 * fecha2.set(Calendar.YEAR, Integer.parseInt(args[5]));
		 * 
		 * //FALTA validar fecha
		 */

		// TEMPORAL para comprobar datos sin linea de comandos

		fecha2.set(Calendar.DAY_OF_MONTH, 8);
		fecha2.set(Calendar.MONTH, 1);
		fecha2.set(Calendar.YEAR, 2015);

		try {
			fecha1.set(Calendar.DAY_OF_MONTH, 2);
			fecha1.set(Calendar.MONTH, 1);
			fecha1.set(Calendar.YEAR, 2015);
		} catch (ArrayIndexOutOfBoundsException s) {
			System.out.println("ERROR");
		}

		// Compruebo si fecha1 posterior a fecha2
		int ordenFechas = fecha1.compareTo(fecha2);
		if (ordenFechas > 0) {
			Calendar fechaAux = new GregorianCalendar();
			fechaAux = fecha1;
			fecha1 = fecha2;
			fecha2 = fechaAux;
		}

		// Muestro datos por pantalla
		System.out.println(fecha1.getTime());
		System.out.println(fecha2.getTime());

		// Compruebo si las fechas coinciden
		if (fecha1.equals(fecha2)) {
			n_years = 0;
			n_weeks = 0;
			int dia = fecha1.get(Calendar.DAY_OF_WEEK);
			if (!(dia == Calendar.SATURDAY || dia == Calendar.SUNDAY))
				n_days = 1;
			System.out.println("\nEntre las dos fechas hay: ");
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
		int diaSemana;
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
		System.out.println("\nEntre las dos fechas hay: ");
		System.out.printf("%.1f Años\n", n_years);
		System.out.println(n_days + " Dias hábiles");
		System.out.printf("%.0f Semanas completas", n_weeks);
		System.exit(0);
	}
}
