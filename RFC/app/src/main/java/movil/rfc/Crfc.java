package movil.rfc;

import android.util.Log;

import java.util.Hashtable;

public class Crfc {

    private String nombre, apellidoPaterno, apellidoMaterno, rfc = "", dia,mes,anio;

    public void setNombre(String nombre) throws rfcException {

        this.nombre = nombre.toUpperCase();
    }

    public void setApellidoPaterno(String apellidoPaterno) throws rfcException {

        this.apellidoPaterno = apellidoPaterno.toUpperCase();
    }

    public void setApellidoMaterno(String apellidoMaterno) throws rfcException{

        this.apellidoMaterno = apellidoMaterno.toUpperCase();
    }
    public void setFecha(String fecha) throws rfcException{

        String fecha2 [] = fecha.split("/");
        this.dia =  fecha2[0];
        this.mes = fecha2[1];
        this.anio = fecha2 [2];
    }
    public String calcularRFC() {

        String Primera = "", Segunda = "", Tercera = "", Cuarta ="";
        boolean FlagUnApellido =  false;
        String nombres [] = {"PENE", "CACA", "JOTO", "SENO", "PIPI", "MIAR"};
        Hashtable <Character, String> valores = llenadoValores();
        Hashtable<Integer,Character> homonimia = tablaHomonomina();
        Hashtable <Character, String> listaCuarto = listaCuarto();

        if (apellidoMaterno.equals(""))
        {
            Primera =  Primera + apellidoPaterno.charAt(0) + apellidoPaterno.charAt(1);
            FlagUnApellido =  true;
        }
        else if (apellidoPaterno.equals(""))
        {
            Primera = Primera + apellidoMaterno.charAt(0) +apellidoMaterno.charAt(1);
            FlagUnApellido = true;
        }
        else {
            if (apellidoPaterno.length() <= 2) {
                Primera = apellidoPaterno.charAt(0) + apellidoMaterno.charAt(0) + nombre.charAt(0) + nombre.charAt(1) + Primera;
            } else {
                if (apellidoPaterno.charAt(1) == 'A' || apellidoPaterno.charAt(1) == 'E'
                        || apellidoPaterno.charAt(1) == 'I' || apellidoPaterno.charAt(1) == 'O' || apellidoPaterno.charAt(1) == 'U')
                    Primera = Primera + apellidoPaterno.charAt(0) + apellidoPaterno.charAt(1);
                else {
                    for (int i = 2; i < apellidoPaterno.length(); i++) {
                        if (apellidoPaterno.charAt(i) == 'A' || apellidoPaterno.charAt(i) == 'E'
                                || apellidoPaterno.charAt(i) == 'I' || apellidoPaterno.charAt(i) == 'O' || apellidoPaterno.charAt(i) == 'U') {
                            Primera = Primera + apellidoPaterno.charAt(0) + apellidoPaterno.charAt(i);
                            break;
                        }
                    }
                }
            }
        }

        if(FlagUnApellido == true)
        {
            Primera = Primera + nombre.charAt(0) + nombre.charAt(1);
        }
        else
        {
            //Nombre
            if(nombre.split(" ").length == 1 )
                Primera =  Primera + apellidoMaterno.charAt(0) + nombre.charAt(0);
            else if (nombre.split(" ")[0].equals("JOSE") || nombre.split(" ")[0].equals("MARIA"))
                Primera = Primera + apellidoMaterno.charAt(0) + nombre.split(" ")[1].charAt(0);
            else
                Primera = Primera + apellidoMaterno.charAt(0) + nombre.split(" ")[0].charAt(0);
        }

        for (String item : nombres)
        {
            if( Primera.equals(item))
            {
                String aux = "";
                for (int i = 0; i < Primera.length()-1 ; i++)
                    aux =  aux + Primera.charAt(i);
                aux = aux + "X";
                Primera =  aux;
            }
        }
        //Años
        Segunda = Segunda + anio.charAt(2) + anio.charAt(3) + mes + dia;
        //Tercera
        String auxTercera = "", aux2Tercera = "";
        int Suma = 0;
        auxTercera = auxTercera + apellidoPaterno + " " + apellidoMaterno + " " + nombre;
        aux2Tercera =  aux2Tercera + "0";
        for (int j = 0; j < auxTercera.length() ; j++)
            aux2Tercera = aux2Tercera + valores.get(auxTercera.charAt(j));
        for (int j = 0; j < aux2Tercera.length() ; j++)
        {
            try
            {
                String auxDigitos =  "" + aux2Tercera.charAt(j) + aux2Tercera.charAt(j+1);
                int auxSuma = Integer.parseInt(auxDigitos) * Integer.parseInt(aux2Tercera.charAt(j+1) +"");
                Suma +=  auxSuma;
            }
            catch (java.lang.StringIndexOutOfBoundsException kiwi)
            {
                break;
            }
        }
        Tercera =  String.valueOf(Suma);
        auxTercera = "";
        for (int j = (Tercera.length()-3); j < Tercera.length() ; j++)
        {
            auxTercera = auxTercera + Tercera.charAt(j);
        }
        int cociente = Integer.parseInt(auxTercera) / 34;
        int residuo = Integer.parseInt(auxTercera) % 34;
        Tercera = "" + String.valueOf(homonimia.get(cociente)) + String.valueOf(homonimia.get(residuo));
        //Cuarta
        rfc = Primera + Segunda + Tercera;
        Suma = 0;
        Log.i("cuartaRFC",rfc);
        for (int j = 0, max = rfc.length()+1; j <  rfc.length(); j++, max--)
        {
            Suma += max * Integer.parseInt(listaCuarto.get(rfc.charAt(j)));
        }
        Log.i("cuartaRFC",String.valueOf(Suma));
        rfc = Primera + Segunda + Tercera + Cuarta;
        int digitoVerificador =  11 - (Suma%11);
        Cuarta = String.valueOf(digitoVerificador);

        rfc = Primera + Segunda + Tercera + Cuarta;
        return rfc;
    }
    public Hashtable<Character, String> llenadoValores()
    {
        Hashtable<Character, String> Lista =  new Hashtable<Character, String>();
        Lista.put(' ',"00");
        Lista.put('0',"00");
        Lista.put('1',"01");
        Lista.put('2',"02");
        Lista.put('3',"03");
        Lista.put('4',"04");
        Lista.put('5',"05");
        Lista.put('6',"06");
        Lista.put('7',"07");
        Lista.put('8',"08");
        Lista.put('9',"09");
        Lista.put('Ñ',"10");
        Lista.put('A',"11");
        Lista.put('B',"12");
        Lista.put('C',"13");
        Lista.put('D',"14");
        Lista.put('E',"15");
        Lista.put('F',"16");
        Lista.put('G',"17");
        Lista.put('H',"18");
        Lista.put('I',"19");
        Lista.put('J',"21");
        Lista.put('K',"22");
        Lista.put('L',"23");
        Lista.put('M',"24");
        Lista.put('N',"25");
        Lista.put('O',"26");
        Lista.put('P',"27");
        Lista.put('Q',"28");
        Lista.put('R',"29");
        Lista.put('S',"32");
        Lista.put('T',"33");
        Lista.put('U',"34");
        Lista.put('V',"35");
        Lista.put('W',"36");
        Lista.put('X',"37");
        Lista.put('Y',"38");
        Lista.put('Z',"39");

        return Lista;
    }
    public Hashtable<Integer,Character> tablaHomonomina ()
    {
        Hashtable<Integer,Character> homonomia = new Hashtable<Integer, Character>();
        homonomia.put(0,'1');
        homonomia.put(1,'2');
        homonomia.put(2,'3');
        homonomia.put(3,'4');
        homonomia.put(4,'5');
        homonomia.put(5,'6');
        homonomia.put(6,'7');
        homonomia.put(7,'8');
        homonomia.put(8,'9');
        homonomia.put(9,'A');
        homonomia.put(10,'B');
        homonomia.put(11,'C');
        homonomia.put(12,'D');
        homonomia.put(13,'E');
        homonomia.put(14,'F');
        homonomia.put(15,'G');
        homonomia.put(16,'H');
        homonomia.put(17,'I');
        homonomia.put(18,'J');
        homonomia.put(19,'K');
        homonomia.put(20,'L');
        homonomia.put(21,'M');
        homonomia.put(22,'N');
        homonomia.put(23,'P');
        homonomia.put(24,'Q');
        homonomia.put(25,'R');
        homonomia.put(26,'S');
        homonomia.put(27,'T');
        homonomia.put(28,'U');
        homonomia.put(39,'V');
        homonomia.put(30,'W');
        homonomia.put(31,'X');
        homonomia.put(32,'Y');
        homonomia.put(33,'Z');

        return homonomia;
    }
    public Hashtable<Character, String> listaCuarto()
    {
        Hashtable<Character, String> Lista =  new Hashtable<Character, String>();
        Lista.put('0',"00");
        Lista.put('1',"01");
        Lista.put('2',"02");
        Lista.put('3',"03");
        Lista.put('4',"04");
        Lista.put('5',"05");
        Lista.put('6',"06");
        Lista.put('7',"07");
        Lista.put('8',"08");
        Lista.put('9',"09");
        Lista.put('A',"10");
        Lista.put('B',"11");
        Lista.put('C',"12");
        Lista.put('D',"13");
        Lista.put('E',"14");
        Lista.put('F',"15");
        Lista.put('G',"16");
        Lista.put('H',"17");
        Lista.put('I',"18");
        Lista.put('J',"19");
        Lista.put('K',"20");
        Lista.put('L',"21");
        Lista.put('M',"22");
        Lista.put('N',"23");
        Lista.put('Ñ',"25");
        Lista.put('O',"25");
        Lista.put('P',"26");
        Lista.put('Q',"27");
        Lista.put('R',"28");
        Lista.put('S',"29");
        Lista.put('T',"30");
        Lista.put('U',"31");
        Lista.put('V',"32");
        Lista.put('W',"33");
        Lista.put('X',"34");
        Lista.put('Y',"35");
        Lista.put('Z',"36");

        return Lista;
    }
}
