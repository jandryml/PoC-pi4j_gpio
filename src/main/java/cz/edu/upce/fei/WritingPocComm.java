package cz.edu.upce.fei;

import com.pi4j.io.gpio.*;

public class WritingPocComm {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("<--Pi4J--> GPIO Listen Example ... started.");

        int value = Integer.parseInt(args[0]);
        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        // provision gpio pin #02 as an input pin with its internal pull down resistor enabled
        GpioPinDigitalMultipurpose mypin = gpio.provisionDigitalMultipurposePin(RaspiPin.GPIO_04, PinMode.DIGITAL_INPUT);

        // Sets it as an Output pin.
        mypin.setMode(PinMode.DIGITAL_OUTPUT);

        if (value != 0) {
            // Sets the state to "high".
            mypin.high();
        } else {
            // Sets the state to "low".
            mypin.low();
        }

        // Sets it as an Input pin.
//        mypin.setMode(PinMode.DIGITAL_INPUT);
    }
}



