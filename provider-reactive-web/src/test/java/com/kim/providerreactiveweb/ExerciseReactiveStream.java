package com.kim.providerreactiveweb;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.List;

public class ExerciseReactiveStream {

    @Test
    public void exercise1() {
        // Use StreamSources.intNumbersStream() and StreamSources.userStream()

        // Print all numbers in the intNumbersStream stream

        // Print numbers from intNumbersStream that are less than 5
        // TODO: Write code here

        // Print the second and third numbers in intNumbersStream that's greater than 5
        // TODO: Write code here

        //  Print the first number in intNumbersStream that's greater than 5.
        //  If nothing is found, print -1
        // TODO: Write code here

        // Print first names of all users in userStream
        // TODO: Write code here

        // Print first names in userStream for users that have IDs from number stream
        // TODO: Write code here
    }

    @Test
    public void exercise2() throws IOException {
        // Use ReactiveSources.intNumbersFlux() and ReactiveSources.userFlux()

        // Print all numbers in the ReactiveSources.intNumbersFlux stream
        Flux<Integer> stream = ReactiveSources.intNumbersFlux();
        stream.subscribe(System.out::println);
        stream.subscribe(e -> System.out.println("another one " + e));

        // Print all users in the ReactiveSources.userFlux stream
//        ReactiveSources.userFlux().subscribe(System.out::println);

        System.out.println("Press a key to end");
        System.in.read();
    }

    @Test
    public void exercise3() {
        // Use ReactiveSources.intNumbersFlux()

        // Get all numbers in the ReactiveSources.intNumbersFlux stream
        // into a List and print the list and its size
        List<Integer> numbers = ReactiveSources.intNumbersFlux().toStream().toList();
        System.out.println("list is "  + numbers);
        System.out.println("size is "  + numbers.size());
    }

    @Test
    public void exercise4() throws IOException {
        // Use ReactiveSources.intNumberMono()

        // Print the value from intNumberMono when it emits
        // TODO: Write code here

        // Get the value from the Mono into an integer variable
        // TODO: Write code here

        System.out.println("Press a key to end");
        System.in.read();
    }

    @Test
    public void exercise5() throws IOException {
        // Use ReactiveSources.intNumberMono() and ReactiveSources.userMono()

        // Subscribe to a flux using the error and completion hooks
        // TODO: Write code here

        // Subscribe to a flux using an implementation of BaseSubscriber
        // TODO: Write code here

        System.out.println("Press a key to end");
        System.in.read();
    }

    @Test
    public void exercise6() throws IOException {
        // Use ReactiveSources.unresponsiveFlux() and ReactiveSources.unresponsiveMono()

        // Get the value from the Mono into a String variable but give up after 5 seconds
        // TODO: Write code here

        // Get the value from unresponsiveFlux into a String list but give up after 5 seconds
        // Come back and do this when you've learnt about operators!
        // TODO: Write code here

        System.out.println("Press a key to end");
        System.in.read();
    }

    @Test
    public void exercise7() throws IOException {
        // Use ReactiveSources.intNumberMono() and ReactiveSources.userMono()

        // Print all values from intNumbersFlux that's greater than 5
        // TODO: Write code here

        // Print 10 times each value from intNumbersFlux that's greater than 5
        // TODO: Write code here

        // Print 10 times each value from intNumbersFlux for the first 3 numbers emitted that's greater than 5
        // TODO: Write code here

        // Print each value from intNumbersFlux that's greater than 20. Print -1 if no elements are found
        // TODO: Write code here

        // Switch ints from intNumbersFlux to the right user from userFlux
        // TODO: Write code here

        // Print only distinct numbers from intNumbersFluxWithRepeat
        // TODO: Write code here

        // Print from intNumbersFluxWithRepeat excluding immediately repeating numbers
        // TODO: Write code here

        System.out.println("Press a key to end");
        System.in.read();
    }

    @Test
    public void exercise8() throws IOException {
        // Use ReactiveSources.intNumbersFluxWithException()

        // Print values from intNumbersFluxWithException and print a message when error happens
        // TODO: Write code here

        // Print values from intNumbersFluxWithException and continue on errors
        // TODO: Write code here

        // Print values from intNumbersFluxWithException and when errors
        // happen, replace with a fallback sequence of -1 and -2
        // TODO: Write code here

        System.out.println("Press a key to end");
        System.in.read();
    }

    @Test
    public void exercise9() throws IOException {
        // Use ReactiveSources.intNumbersFlux()

        // Print size of intNumbersFlux after the last item returns
        // TODO: Write code here

        // Collect all items of intNumbersFlux into a single list and print it
        // TODO: Write code here

        // Transform to a sequence of sums of adjacent two numbers
        // TODO: Write code here

        System.out.println("Press a key to end");
        System.in.read();
    }
}
