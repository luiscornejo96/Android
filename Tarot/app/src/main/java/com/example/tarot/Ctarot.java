package com.example.tarot;

public class Ctarot {

    private int dia, mes, anio;
    private boolean Bisiesto = false;

    public void setDia(int dia) throws FechaException{

        if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12)
        {
            if(dia == 0 || dia > 31)
                throw new FechaException("Dia Invalido!!");
            else
                this.dia = dia;
        }
        else if( mes == 4 || mes == 6 || mes == 9 || mes == 11)
        {
            if(dia == 0 || dia > 30)
                throw new FechaException("Dia Invalido!!");
            else
                this.dia = dia;
        }
        else if( mes == 2)
        {
            if( dia == 0 || dia > 29)
            {
                throw new FechaException("Dia Invalido");
            }
            else if( dia == 29 && Bisiesto == false)
            {
                throw new FechaException("Dia Invalido");
            }
            else if( dia == 29 && Bisiesto == true)
            {
                this.dia = dia;
            }

        }
    }

    public void setMes(int mes) throws FechaException {
        if(mes < 1 || mes >12)
            throw new FechaException("Mes Invalido");
        else
            this.mes = mes;
    }

    public void setAnio(int anio) throws FechaException {
        if((anio % 400 == 0) || (anio % 4 == 0 && anio % 100 != 0))
        {
            this.anio = anio;
            Bisiesto = true;
        }
        else
        {
            this.anio = anio;
            Bisiesto =  false;
        }
    }
    public int calcularTarot()
    {
        int suma = 0, entero = 0, residuo = 0;
        suma =dia + mes + anio;
        while(suma >= 10)
        {
            residuo = suma % 10;
            entero = (suma-residuo) / 10;
            suma = entero + residuo;
        }
        return suma;
    }
}
