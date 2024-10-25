package gt.edu.umg.mycalculator;

import android.util.Log;

public class IntegralCalculator {

    public static double calcularAreaBajoCurva(String funcion, double a, double b, int n) {
        if (a >= b) throw new IllegalArgumentException("El límite inferior debe ser menor que el superior");

        double h = (b - a) / n;  // Tamaño del paso
        double suma = 0.0;

        // Log para depuración
        Log.d("Calculadora", "Calculando integral de " + funcion + " desde " + a + " hasta " + b);
        Log.d("Calculadora", "Tamaño de paso h = " + h);

        // Aplicar regla del trapecio
        for (int i = 0; i <= n; i++) {
            double x = a + i * h;
            CalculadoraActivity.Calculadora.setValorX(x);

            // Factor es 1.0 para los extremos, 2.0 para puntos intermedios
            double factor = (i == 0 || i == n) ? 1.0 : 2.0;

            double fx = CalculadoraActivity.Calculadora.evaluar(funcion);
            suma += factor * fx;

            // Log para depuración
            Log.d("Calculadora", "x = " + x + ", f(x) = " + fx);
        }

        double resultado = (h / 2.0) * suma;
        Log.d("Calculadora", "Resultado final = " + resultado);

        return resultado;
    }

    public static double calcularIntegralDefinida(String funcion, double a, double b, int n) {
        if (n % 2 != 0) n++; // Aseguramos que n sea par

        double h = (b - a) / n;
        double suma = 0;

        for (int i = 2; i < n; i += 2) {
            CalculadoraActivity.Calculadora.setValorX(a + i*h);  // Corregido aquí
            suma += 2 * CalculadoraActivity.Calculadora.evaluar(funcion);  // Corregido aquí
        }

        for (int i = 1; i < n; i += 2) {
            CalculadoraActivity.Calculadora.setValorX(a + i*h);  // Corregido aquí
            suma += 4 * CalculadoraActivity.Calculadora.evaluar(funcion);  // Corregido aquí
        }

        CalculadoraActivity.Calculadora.setValorX(a);
        suma += CalculadoraActivity.Calculadora.evaluar(funcion);  // Corregido aquí
        CalculadoraActivity.Calculadora.setValorX(b);
        suma += CalculadoraActivity.Calculadora.evaluar(funcion);  // Corregido aquí

        return (h/3) * suma;
    }

    public static double calcularIntegralImpropia(String funcion, double a, double b) {
        if (Double.isInfinite(b)) {
            // Caso límite superior infinito
            return calcularIntegralDefinida(funcion, a, 100, 1000); // Aproximación
        } else if (Double.isInfinite(a)) {
            // Caso límite inferior infinito
            return calcularIntegralDefinida(funcion, -100, b, 1000); // Aproximación
        }
        return calcularIntegralDefinida(funcion, a, b, 1000);
    }

    public static double calcularValorPromedio(String funcion, double a, double b) {
        double integral = calcularIntegralDefinida(funcion, a, b, 1000);
        return integral / (b - a);
    }

    public static double calcularVolumenArandelas(String funcion, double a, double b, int n) {
        if (a >= b) {
            throw new IllegalArgumentException("El límite inferior debe ser menor que el superior");
        }

        double h = (b - a) / n;
        double suma = 0.0;

        Log.d("Calculadora", "Calculando volumen por arandelas de " + funcion + " desde " + a + " hasta " + b);
        Log.d("Calculadora", "Tamaño de paso h = " + h);

        try {
            for (int i = 0; i <= n; i++) {
                double x = a + i * h;
                CalculadoraActivity.Calculadora.setValorX(x);

                double factor = (i == 0 || i == n) ? 1.0 : 2.0;

                double fx = CalculadoraActivity.Calculadora.evaluar(funcion);

                double fx_cuadrado = fx * fx;

                suma += factor * fx_cuadrado;

                Log.d("Calculadora", "x = " + x + ", f(x) = " + fx + ", f(x)^2 = " + fx_cuadrado);
            }

            double resultado = Math.PI * (h / 2.0) * suma;

            Log.d("Calculadora", "Volumen final = " + resultado);
            return resultado;

        } catch (Exception e) {
            Log.e("Calculadora", "Error al calcular volumen por arandelas", e);
            throw new RuntimeException("Error en el cálculo del volumen: " + e.getMessage());
        }
    }

    public static double calcularVolumenCascarones(String funcion, double a, double b, int n) {
        if (a >= b) {
            throw new IllegalArgumentException("El límite inferior debe ser menor que el superior");
        }

        // Tamaño del paso
        double h = (b - a) / n;
        double suma = 0.0;

        Log.d("Calculadora", "Calculando volumen por cáscarones de " + funcion + " desde " + a + " hasta " + b);
        Log.d("Calculadora", "Tamaño de paso h = " + h);

        try {
            // Aplicamos la regla del trapecio
            for (int i = 0; i <= n; i++) {
                double x = a + i * h;
                CalculadoraActivity.Calculadora.setValorX(x);

                // Factor es 1.0 para los extremos, 2.0 para puntos intermedios
                double factor = (i == 0 || i == n) ? 1.0 : 2.0;

                // Calculamos f(x)
                double fx = CalculadoraActivity.Calculadora.evaluar(funcion);

                // Para cáscarones, multiplicamos por x
                double x_fx = x * fx;

                suma += factor * x_fx;

                Log.d("Calculadora", "x = " + x + ", f(x) = " + fx + ", x*f(x) = " + x_fx);
            }

            // Multiplicamos por 2π y por (h/2) para completar la fórmula
            double resultado = 2 * Math.PI * (h / 2.0) * suma;

            Log.d("Calculadora", "Volumen final = " + resultado);
            return resultado;

        } catch (Exception e) {
            Log.e("Calculadora", "Error al calcular volumen por cáscarones", e);
            throw new RuntimeException("Error en el cálculo del volumen: " + e.getMessage());
        }
    }
    public static double calcularVolumenDiscos(String funcion, double a, double b, int n) {
        if (a >= b) {
            throw new IllegalArgumentException("El límite inferior debe ser menor que el superior");
        }

        // Tamaño del paso
        double h = (b - a) / n;
        double suma = 0.0;

        Log.d("Calculadora", "Calculando volumen por discos de " + funcion + " desde " + a + " hasta " + b);
        Log.d("Calculadora", "Tamaño de paso h = " + h);

        try {
            // Aplicamos la regla del trapecio
            for (int i = 0; i <= n; i++) {
                double x = a + i * h;
                CalculadoraActivity.Calculadora.setValorX(x);

                // Factor es 1.0 para los extremos, 2.0 para puntos intermedios
                double factor = (i == 0 || i == n) ? 1.0 : 2.0;

                // Calculamos f(x)
                double fx = CalculadoraActivity.Calculadora.evaluar(funcion);

                // Para discos, necesitamos [f(x)]^2
                double fx_cuadrado = fx * fx;

                suma += factor * fx_cuadrado;

                Log.d("Calculadora", "x = " + x + ", f(x) = " + fx + ", f(x)^2 = " + fx_cuadrado);
            }

            // Multiplicamos por π y por (h/2) para completar la fórmula
            double resultado = Math.PI * (h / 2.0) * suma;

            Log.d("Calculadora", "Volumen final = " + resultado);
            return resultado;

        } catch (Exception e) {
            Log.e("Calculadora", "Error al calcular volumen por discos", e);
            throw new RuntimeException("Error en el cálculo del volumen: " + e.getMessage());
        }
    }

    public static double calcularIntegralTrigonometrica(String funcion, double a, double b, int n) {
        if (a >= b) {
            throw new IllegalArgumentException("El límite inferior debe ser menor que el superior");
        }

        // Tamaño del paso
        double h = (b - a) / n;
        double suma = 0.0;

        Log.d("Calculadora", "Calculando integral trigonométrica de " + funcion + " desde " + a + " hasta " + b);
        Log.d("Calculadora", "Tamaño de paso h = " + h);

        try {
            // Aplicamos la regla de Simpson compuesta para mayor precisión con funciones trigonométricas
            // Evaluamos el primer y último punto
            CalculadoraActivity.Calculadora.setValorX(a);
            suma += CalculadoraActivity.Calculadora.evaluar(funcion);

            CalculadoraActivity.Calculadora.setValorX(b);
            suma += CalculadoraActivity.Calculadora.evaluar(funcion);

            // Sumamos los puntos pares (factor 2)
            for (int i = 2; i < n; i += 2) {
                double x = a + i * h;
                CalculadoraActivity.Calculadora.setValorX(x);
                suma += 2 * CalculadoraActivity.Calculadora.evaluar(funcion);
            }

            // Sumamos los puntos impares (factor 4)
            for (int i = 1; i < n; i += 2) {
                double x = a + i * h;
                CalculadoraActivity.Calculadora.setValorX(x);
                suma += 4 * CalculadoraActivity.Calculadora.evaluar(funcion);
            }

            // Multiplicamos por h/3 según la regla de Simpson
            double resultado = (h / 3.0) * suma;

            Log.d("Calculadora", "Resultado final = " + resultado);
            return resultado;

        } catch (Exception e) {
            Log.e("Calculadora", "Error al calcular integral trigonométrica", e);
            throw new RuntimeException("Error en el cálculo de la integral: " + e.getMessage());
        }
    }

    public static double calcularIntegralSustitucion(String funcionOriginal, String sustitucion,
                                                     double a, double b, int n) {
        if (a >= b) {
            throw new IllegalArgumentException("El límite inferior debe ser menor que el superior");
        }

        double h = (b - a) / n;
        double suma = 0.0;

        Log.d("Calculadora", "Calculando integral por sustitución de " + funcionOriginal);
        Log.d("Calculadora", "Usando sustitución: u = " + sustitucion);
        Log.d("Calculadora", "Límites: " + a + " a " + b);

        try {
            // Aplicamos la regla de Simpson compuesta
            // Evaluamos primer y último punto
            CalculadoraActivity.Calculadora.setValorX(a);
            double u_a = CalculadoraActivity.Calculadora.evaluar(sustitucion);
            CalculadoraActivity.Calculadora.setValorX(b);
            double u_b = CalculadoraActivity.Calculadora.evaluar(sustitucion);

            // Ajustamos los límites según la sustitución
            double h_u = (u_b - u_a) / n;

            // Evaluamos la integral con la sustitución
            for (int i = 0; i <= n; i++) {
                double u = u_a + i * h_u;
                // Establecemos u como la variable para evaluar
                CalculadoraActivity.Calculadora.setValorX(u);

                // Factor es 1.0 para los extremos, 4.0 para impares, 2.0 para pares
                double factor;
                if (i == 0 || i == n) {
                    factor = 1.0;
                } else if (i % 2 == 1) {
                    factor = 4.0;
                } else {
                    factor = 2.0;
                }

                double fx = CalculadoraActivity.Calculadora.evaluar(funcionOriginal);
                suma += factor * fx;

                Log.d("Calculadora", "u = " + u + ", f(u) = " + fx);
            }

            // Multiplicamos por h/3 según la regla de Simpson
            double resultado = (h_u / 3.0) * suma;

            Log.d("Calculadora", "Resultado final = " + resultado);
            return resultado;

        } catch (Exception e) {
            Log.e("Calculadora", "Error al calcular integral por sustitución", e);
            throw new RuntimeException("Error en el cálculo: " + e.getMessage());
        }
    }

    public static double calcularValorMedioIntegral(String funcion, double a, double b, int n) {
        if (a >= b) {
            throw new IllegalArgumentException("El límite inferior debe ser menor que el superior");
        }

        // Tamaño del paso
        double h = (b - a) / n;
        double suma = 0.0;

        Log.d("Calculadora", "Calculando valor medio de " + funcion + " desde " + a + " hasta " + b);
        Log.d("Calculadora", "Tamaño de paso h = " + h);

        try {
            // Aplicamos la regla de Simpson compuesta para mayor precisión
            // Evaluamos el primer y último punto
            CalculadoraActivity.Calculadora.setValorX(a);
            suma += CalculadoraActivity.Calculadora.evaluar(funcion);

            CalculadoraActivity.Calculadora.setValorX(b);
            suma += CalculadoraActivity.Calculadora.evaluar(funcion);

            // Sumamos los puntos pares (factor 2)
            for (int i = 2; i < n; i += 2) {
                double x = a + i * h;
                CalculadoraActivity.Calculadora.setValorX(x);
                suma += 2 * CalculadoraActivity.Calculadora.evaluar(funcion);
            }

            // Sumamos los puntos impares (factor 4)
            for (int i = 1; i < n; i += 2) {
                double x = a + i * h;
                CalculadoraActivity.Calculadora.setValorX(x);
                suma += 4 * CalculadoraActivity.Calculadora.evaluar(funcion);
            }

            // Calculamos primero la integral con la regla de Simpson
            double integral = (h / 3.0) * suma;

            // Calculamos el valor medio dividiendo por la longitud del intervalo
            double valorMedio = integral / (b - a);

            Log.d("Calculadora", "Integral = " + integral);
            Log.d("Calculadora", "Valor medio = " + valorMedio);

            return valorMedio;

        } catch (Exception e) {
            Log.e("Calculadora", "Error al calcular valor medio", e);
            throw new RuntimeException("Error en el cálculo: " + e.getMessage());
        }
    }

    public static double encontrarPuntoValorMedio(String funcion, double a, double b,
                                                  double valorMedio, int maxIteraciones) {
        double tolerancia = 0.0001;
        double x = a;
        double paso = (b - a) / maxIteraciones;

        try {
            while (x <= b) {
                CalculadoraActivity.Calculadora.setValorX(x);
                double fx = CalculadoraActivity.Calculadora.evaluar(funcion);

                if (Math.abs(fx - valorMedio) < tolerancia) {
                    return x;
                }
                x += paso;
            }
            return Double.NaN; // No se encontró el punto
        } catch (Exception e) {
            Log.e("Calculadora", "Error al buscar punto de valor medio", e);
            return Double.NaN;
        }
    }

    public static double calcularIntegralPorPartes(String funcionU, String funcionDV,
                                                   double a, double b, int n) {
        if (a >= b) {
            throw new IllegalArgumentException("El límite inferior debe ser menor que el superior");
        }

        double h = (b - a) / n;
        double suma = 0.0;

        Log.d("Calculadora", "Calculando integral por partes de u=" + funcionU + ", dv=" + funcionDV);

        try {
            // Primero calculamos v integrando dv
            // Para este ejemplo, asumimos que ya tenemos la integral de dv
            String funcionV = funcionDV; // En la práctica, necesitarías integrar dv

            // Calculamos la derivada de u
            String funcionDU = funcionU; // En la práctica, necesitarías derivar u

            // Calculamos uv en los límites
            CalculadoraActivity.Calculadora.setValorX(b);
            double uv_superior = CalculadoraActivity.Calculadora.evaluar(funcionU) *
                    CalculadoraActivity.Calculadora.evaluar(funcionV);

            CalculadoraActivity.Calculadora.setValorX(a);
            double uv_inferior = CalculadoraActivity.Calculadora.evaluar(funcionU) *
                    CalculadoraActivity.Calculadora.evaluar(funcionV);

            // Calculamos la integral de v*du usando regla de Simpson
            for (int i = 0; i <= n; i++) {
                double x = a + i * h;
                CalculadoraActivity.Calculadora.setValorX(x);

                double factor = (i == 0 || i == n) ? 1.0 : (i % 2 == 0 ? 2.0 : 4.0);

                double v = CalculadoraActivity.Calculadora.evaluar(funcionV);
                double du = CalculadoraActivity.Calculadora.evaluar(funcionDU);

                suma += factor * (v * du);
            }

            double integral_vdu = (h / 3.0) * suma;

            // Aplicamos la fórmula de integración por partes
            double resultado = uv_superior - uv_inferior - integral_vdu;

            Log.d("Calculadora", "uv en límites = " + (uv_superior - uv_inferior));
            Log.d("Calculadora", "Integral v*du = " + integral_vdu);
            Log.d("Calculadora", "Resultado final = " + resultado);

            return resultado;

        } catch (Exception e) {
            Log.e("Calculadora", "Error al calcular integral por partes", e);
            throw new RuntimeException("Error en el cálculo: " + e.getMessage());
        }
    }

    public static double calcularDerivadaParcial(String funcion, char variable,
                                                 double x, double y, double h) {
        try {
            Log.d("Calculadora", "Calculando derivada parcial de " + funcion +
                    " respecto a " + variable);

            // Guardamos los valores originales
            double valorOriginalX = x;
            double valorOriginalY = y;

            // Evaluamos en el punto original
            CalculadoraActivity.Calculadora.setValorX(x);
            CalculadoraActivity.Calculadora.setValorY(y);
            double f0 = CalculadoraActivity.Calculadora.evaluar(funcion);

            // Evaluamos con un pequeño incremento en la variable correspondiente
            if (variable == 'x') {
                CalculadoraActivity.Calculadora.setValorX(x + h);
                CalculadoraActivity.Calculadora.setValorY(y);
            } else {
                CalculadoraActivity.Calculadora.setValorX(x);
                CalculadoraActivity.Calculadora.setValorY(y + h);
            }
            double f1 = CalculadoraActivity.Calculadora.evaluar(funcion);

            // Calculamos la derivada usando diferencias finitas
            double derivada = (f1 - f0) / h;

            Log.d("Calculadora", "Resultado de la derivada parcial = " + derivada);
            return derivada;

        } catch (Exception e) {
            Log.e("Calculadora", "Error al calcular derivada parcial", e);
            throw new RuntimeException("Error en el cálculo: " + e.getMessage());
        }
    }

    /**
     * Calcula la integral de una derivada parcial en un intervalo.
     */
    public static double calcularIntegralDerivadaParcial(String funcion, char variable,
                                                         double a, double b, double y, int n) {
        if (a >= b) {
            throw new IllegalArgumentException("El límite inferior debe ser menor que el superior");
        }

        double h = (b - a) / n;
        double suma = 0.0;
        double h_derivada = 0.0001; // Paso para la aproximación de la derivada

        try {
            // Aplicamos la regla de Simpson para integrar la derivada parcial
            for (int i = 0; i <= n; i++) {
                double x = a + i * h;
                double factor = (i == 0 || i == n) ? 1.0 : (i % 2 == 0 ? 2.0 : 4.0);

                // Calculamos la derivada parcial en este punto
                double derivada = calcularDerivadaParcial(funcion, variable, x, y, h_derivada);
                suma += factor * derivada;
            }

            double resultado = (h / 3.0) * suma;

            Log.d("Calculadora", "Resultado de la integral = " + resultado);
            return resultado;

        } catch (Exception e) {
            Log.e("Calculadora", "Error al calcular integral de derivada parcial", e);
            throw new RuntimeException("Error en el cálculo: " + e.getMessage());
        }
    }
}
