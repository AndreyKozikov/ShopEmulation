import Entity.Customer;
import Entity.Order;
import Entity.Product;
import Enumerators.Holiday;
import Exceptions.AmountException;
import Exceptions.CustomerException;
import Exceptions.ProductException;

public class ShopEmulation {

    public static void main(String[] args) throws CustomerException, AmountException, ProductException {
        // Создание массива покупателей
        Customer[] customers = {
                new Customer("Иван Иванов", 30, "1234567890", Customer.Gender.MALE),
                new Customer("Мария Смирнова", 25, "0987654321", Customer.Gender.FEMALE)
        };


        // Создание массива товаров
        Product[] products = {
                new Product("Телефон", 15000),
                new Product("Ноутбук", 45000),
                new Product("Часы", 5000),
                new Product("Планшет", 20000),
                new Product("Книга", 700)
        };

        // Массив заказов
        Order[] orders = new Order[5];
        int orderCount = 0;

        // Попытка создания заказов
        try {
            orders[orderCount++] = makePurchase(customers, products, "Иван Иванов", "Ноутбук", 1);
            orders[orderCount++] = makePurchase(customers, products, "Мария Смирнова", "Книга", 100);
           // orders[orderCount++] = makePurchase(customers, products, "Неизвестный", "Часы", 1); // Ошибка!
        } catch (CustomerException e) {
            System.err.println("Критическая ошибка: " + e.getMessage());
            return; // Завершаем приложение
        } catch (ProductException e) {
            System.err.println("Ошибка товара: " + e.getMessage());
        } catch (AmountException e) {
            System.err.println("Ошибка количества: " + e.getMessage());
            // Ставим количество = 1
            orders[orderCount++] = makePurchase(customers, products, "Мария Смирнова", "Часы", 1);
        }

        // Вывод итогового количества совершённых покупок
        System.out.println("Итоговое количество совершённых покупок: " + orderCount);

        // Поздравления
        System.out.println("\nПоздравления:");
        GreetingHelper.sendGreetings(customers, Holiday.NEW_YEAR);
        GreetingHelper.sendGreetings(customers, Holiday.WOMEN_DAY);
        GreetingHelper.sendGreetings(customers, Holiday.DEFENDER_DAY);
    }

    // Метод совершения покупки
    public static Order makePurchase(Customer[] customers, Product[] products,
                                     String customerName, String productName, int quantity)
            throws CustomerException, ProductException, AmountException {
        // Проверка покупателя
        Customer customer = null;
        for (Customer c : customers) {
            if (c.getFullName().equals(customerName)) {
                customer = c;
                break;
            }
        }
        if (customer == null) {
            throw new CustomerException("Покупатель " + customerName + " не найден!");
        }

        // Проверка товара
        Product product = null;
        for (Product p : products) {
            if (p.getName().equals(productName)) {
                product = p;
                break;
            }
        }
        if (product == null) {
            throw new ProductException("Товар " + productName + " не найден!");
        }

        // Проверка количества
        if (quantity <= 0 || quantity > 100) {
            throw new AmountException("Некорректное количество: " + quantity);
        }

        return new Order(customer, product, quantity);
    }

    // Статический вложенный класс для поздравлений
    public static class GreetingHelper {
        public static void sendGreetings(Customer[] customers, Holiday holiday) {
            for (Customer customer : customers) {
                switch (holiday) {
                    case NEW_YEAR:
                        System.out.println("С Новым Годом, " + customer.getFullName() + "!");
                        break;
                    case WOMEN_DAY:
                        if (customer.getGender() == Customer.Gender.FEMALE) {
                            System.out.println("С 8 марта, " + customer.getFullName() + "!");
                        }
                        break;
                    case DEFENDER_DAY:
                        if (customer.getGender() == Customer.Gender.MALE) {
                            System.out.println("С 23 февраля, " + customer.getFullName() + "!");
                        }
                        break;
                    default:
                        System.out.println(customer.getFullName() + ": Сегодня нет праздника.");
                }
            }
        }
    }
}
