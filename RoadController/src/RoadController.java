import core.*;
import core.Camera;

import java.util.Scanner;

public class RoadController {
    private static double passengerCarMaxWeight = 3500.0; // kg здесь объявляется переменная
    private static int passengerCarMaxHeight = 2000; // mm здесь объявляется переменная
    private static int controllerMaxHeight = 4000; // mm здесь объявляется переменная

    private static int passengerCarPrice = 100; // RUB здесь объявляется переменная
    private static int cargoCarPrice = 250; // RUB здесь объявляется переменная
    private static int vehicleAdditionalPrice = 200; // RUB здесь объявляется переменная

    public static void main(String[] args) {
        System.out.println("Сколько автомобилей сгенерировать?");

        Scanner scanner = new Scanner(System.in);
        int carsCount = scanner.nextInt(); //здесь объявляется переменная

        for (int i = 0; i < carsCount; i++) { //здесь объявляется переменная
            Car car = Camera.getNextCar();
            System.out.println(car);

            //Пропускаем автомобили спецтранспорта бесплатно
            if (car.isSpecial) {
                openWay();
                continue;
            }

            //Проверяем высоту и массу автомобиля, вычисляем стоимость проезда
            int price = calculatePrice(car); //здесь объявляется переменная
            if (price == -1) {
                continue;
            }

            System.out.println("Общая сумма к оплате: " + price + " руб.");
        }
    }

    /**
     * Расчёт стоимости проезда исходя из массы и высоты
     */
    private static int calculatePrice(Car car) {
        int carHeight = car.height; // здесь объявляется переменная
        int price = 0; // здесь объявляется переменная
        if (carHeight > controllerMaxHeight) {
            blockWay("высота вашего ТС превышает высоту пропускного пункта!");
            return -1;
        } else if (carHeight > passengerCarMaxHeight) {
            double weight = car.weight;
            //Грузовой автомобиль
            if (weight > passengerCarMaxWeight) {
                price = passengerCarPrice;
                if (car.hasVehicle) {
                    price = price + vehicleAdditionalPrice;
                }
            }
            //Легковой автомобиль
            else {
                price = cargoCarPrice;
            }
        } else {
            price = passengerCarPrice;
        }
        return price;
    }

    /**
     * Открытие шлагбаума
     */
    private static void openWay() {
        System.out.println("Шлагбаум открывается... Счастливого пути!");
    }

    /**
     * Сообщение о невозможности проезда
     */
    private static void blockWay(String reason) {
        System.out.println("Проезд невозможен: " + reason);
    }
}