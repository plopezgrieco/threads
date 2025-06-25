package threads;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class EcuacionSegundoGradoAsync {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        double a = 1;
        double b = -3;
        double c = 2;

        // 1. Calcular b^2
        CompletableFuture<Double> bCuadrado = CompletableFuture.supplyAsync(() -> {
        	ThreadUtil.sleep();
            System.out.println("Calculando b^2");
            return b * b;
        });

        // 2. Calcular 4.a.c
        CompletableFuture<Double> cuatroAC = CompletableFuture.supplyAsync(() -> {
        	ThreadUtil.sleep();
            System.out.println("Calculando 4.a.c");
            return 4 * a * c;
        });

        // 3. Calcular discriminante => b^2 - 4.a.c
        //    Para calcularlo, a bCuadrado se lo combina con cuatroAC. Esa combinación se realiza 
        //	  cuando ambos terminen, con   thenCombine y le asamos una bifunction
        CompletableFuture<Double> discriminante = bCuadrado.thenCombine(cuatroAC, (b2, ac4) -> {
        	ThreadUtil.sleep();
            System.out.println("Calculando discriminante");
            return b2 - ac4;
        });

        // 4. Calcular la raiz cuadrada del discriminante
        //    Para este, tiene que haber calculado previamente el discriminante. usamos thenApply y 
        //    le pasamos una Function
        //    Si el discriminante es menor que 0 lanzamos una excecpción
        CompletableFuture<Double> raizDiscriminante = discriminante.thenApply(d -> {
        	ThreadUtil.sleep();
            if (d < 0) throw new RuntimeException("Discriminante negativo: no hay raíces reales");
            System.out.println("Calculando raíz del discriminante");
            return Math.sqrt(d);
        });

        // 5. Calcular -b
        CompletableFuture<Double> menosB = CompletableFuture.supplyAsync(() -> {
        	ThreadUtil.sleep();
            System.out.println("Calculando -b");
            return -b;
        });

        // 6. Calcular 2.a
        CompletableFuture<Double> dosA = CompletableFuture.supplyAsync(() -> {
            ThreadUtil.sleep();
            System.out.println("Calculando 2a");
            return 2 * a;
        });

        // 7. Calcular x1 = (-b + raiz discriminante) / (2.a)
        //    combinamos menosB con raizDiscriminante y al resultado lo combinamos con dosA
        CompletableFuture<Double> x1 = menosB
                .thenCombine(raizDiscriminante, Double::sum)
                .thenCombine(dosA, (num, den) -> num / den);

        // 8. Calcular x2 = (-b - √Δ) / (2a)
        CompletableFuture<Double> x2 = menosB
                .thenCombine(raizDiscriminante, (mb, r) -> mb - r)
                .thenCombine(dosA, (num, den) -> num / den);

        // 9. Mostrar resultados
        //    Los extraemos de los CompletableFuture x1 y x2 con el método get, que se ejecutará al 
        //    finalizar el trabajo
        System.out.println("x1 = " + x1.get());
        System.out.println("x2 = " + x2.get());
        
        // Si lo ejecutáis varias veces, veréis que los cálculos van por separado y en distinto orden en 
        //   distintas ejecuciones por el sleep() que hicimos random
    }
}
