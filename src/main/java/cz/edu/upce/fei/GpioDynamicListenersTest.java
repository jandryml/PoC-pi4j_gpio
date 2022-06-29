package cz.edu.upce.fei;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class GpioDynamicListenersTest {

    public static void main(String args[]) throws InterruptedException {
        System.out.println("<--Pi4J--> GPIO Listen Example ... started.");


        // keep program running until user aborts (CTRL-C)
        while (true) {
            System.out.println("Creating listeners");

            createListener(RaspiPin.GPIO_02, 42);
            createListener(RaspiPin.GPIO_03, 420);

            System.out.println("Listeners created.");

            Thread.sleep(1000);
        }

        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        // gpio.shutdown();   <--- implement this method call if you wish to terminate the Pi4J GPIO controller
    }

    private static void createListener(Pin pin, int value) {
        final GpioController gpio = GpioFactory.getInstance();

        // provision gpio pin #02 as an input pin with its internal pull down resistor enabled
        final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(pin);

        // set shutdown state for this input pin
        myButton.setShutdownOptions(true);

        // create and register gpio pin listener
        myButton.addListener((GpioPinListenerDigital) event -> {
            // display pin state on console
            System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
            System.out.println("Value is: " + value);
        });
    }
}
