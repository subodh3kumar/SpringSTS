package workshop;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class SpringMonoFluxTest {

    @Test
    @Disabled
    public void testMono() {
        Mono<String> mono = Mono.just("Hello World");
        mono.subscribe(System.out::println);
    }

    @Test
    @Disabled
    public void testMonoLog() {
        Mono<String> mono = Mono.just("Hello World").log();
        mono.subscribe(System.out::println);
    }

    @Test
    @Disabled
    public void testOnError() {
        Mono<?> mono = Mono.just("Hello World")
                .then(Mono.error(new RuntimeException("onError Mono")))
                .log();
        mono.subscribe(System.out::println, (e) -> System.out.println(e.getLocalizedMessage()));
    }

    @Test
    @Disabled
    public void testFlux() {
        Flux<String> flux = Flux.just("Java", "Spring", "Hibernate", "Quarkus").log();
        flux.subscribe(System.out::println);
    }

    @Test
    @Disabled
    public void testFluxConcat() {
        Flux<String> flux = Flux.just("Java", "Spring", "Hibernate", "Quarkus")
                .concatWithValues("Micronaut")
                .log();
        flux.subscribe(System.out::println);
    }

    @Test
    @Disabled
    public void testFluxConcatError() {
        Flux<String> flux = Flux.just("Java", "Spring", "Hibernate", "Quarkus")
                .concatWithValues("Micronaut")
                .concatWith(Flux.error(new RuntimeException("onError Flux")))
                .log();
        flux.subscribe(System.out::println, e -> System.out.println(e.getLocalizedMessage()));
    }

    @Test
    public void testFluxConcatErrorConcat() {
        Flux<String> flux = Flux.just("Java", "Spring", "Hibernate", "Quarkus")
                .concatWithValues("Micronaut")
                .concatWith(Flux.error(new RuntimeException("onError Flux")))
                .concatWithValues("AWS")
                .log();
        flux.subscribe(System.out::println, e -> System.out.println(e.getLocalizedMessage()));
    }
}
