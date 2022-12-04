import java.time.LocalDate;
import java.util.ArrayList;

public class MEWWWsAnimalShop
{
    public static void main(String[] args)
    {
        System.out.println("欢迎来到MEWWWの宠物店！！！！\r\n");
        Test test1 = new Test();
    }
}

abstract class Animal
{
    protected String name;
    protected int age;
    protected String sex;
    protected double price;

    public Animal(String name, int age, String sex, double price)
    {
        this.name = name;
        this.age = age;
        this.price = price;
        this.sex = sex;
    }

    public abstract String toString();

    public String getSex()
    {
        return sex;
    }

    public double getPrice()
    {
        return price;
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }
}

class ChineseDog extends Animal
{
    private boolean isVaccineInjected;

    public ChineseDog(String name, int age, String sex, double price)
    {
        super(name, age, sex, 100);
    }

    @Override
    public String toString()
    {
        return "狗狗名字:" + getName() + "  狗狗年龄:" + getAge() + "  狗狗性别:" + getSex() + "  狗狗价格:" + getPrice() + "\r\n";
    }
}

class Cat extends Animal
{
    public Cat(String name, int age, String sex, double price)
    {
        super(name, age, sex, 200);
    }

    @Override
    public String toString()
    {
        return "猫猫名字:" + getName() + "  猫猫年龄:" + getAge() + "  猫猫性别:" + getSex() + "  猫猫价格:" + getPrice() + "\r\n";
    }
}

class ozline extends Animal
{
    public ozline(String name, int age, String sex, double price)
    {
        super(name, age, sex, 114514);
    }

    @Override
    public String toString()
    {
        return "小黄老师名字:" + getName() + "  小黄老师年龄:" + getAge() + "  小黄老师性别:" + getSex() + "  小黄老师价格:" + getPrice() + "\r\n";
    }
}

class Customer
{
    private String name;
    private int timesOfVisit;
    private LocalDate latest;

    public Customer(String name, int timesOfVisit, LocalDate latest)
    {
        this.name = name;
        this.latest = latest;
        this.timesOfVisit = timesOfVisit;
    }

    public String getName()
    {
        return name;
    }

    public int getTimesOfVisit()
    {
        return timesOfVisit;
    }

    public LocalDate getLatest()
    {
        return latest;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setLatest(LocalDate latest)
    {
        this.latest = latest;
    }

    public void setTimesOfVisit(int timesOfVisit)
    {
        this.timesOfVisit = timesOfVisit;
    }

    @Override
    public String toString()
    {
        return "顾客名字:" + name + "  到店次数" + timesOfVisit + "  最新到店时间" + latest;
    }
}

interface AnimalShop
{
    void buyANewAnimal(Animal animal);

    void pleaseTheCustomer(Customer customer, String animalToBuy);

    void close(LocalDate time);
}

class MyAnimalShop implements AnimalShop
{
    private double savings;
    private ArrayList<Animal> animalList;
    private ArrayList<Customer> customerList;
    private boolean isInBusiness;

    public MyAnimalShop(double savings, ArrayList<Animal> animalList, ArrayList<Customer> customerList)
    {
        this.savings = savings;
        this.animalList = animalList;
        this.customerList = customerList;
    }


    public ArrayList<Animal> getAnimalList()
    {
        return animalList;
    }

    public ArrayList<Customer> getCustomerList()
    {
        return customerList;
    }

    public double getSavings()
    {
        return savings;
    }

    public void setSavings(double savings)
    {
        this.savings = savings;
    }

    @Override
    public void buyANewAnimal(Animal animal)
    {
        if (savings < animal.getPrice())
        {
            throw new InsufficientBalanceException("不够钱买qwq");
        }
        animalList.add(animal);
        savings -= animal.price;
    }

    @Override
    public void pleaseTheCustomer(Customer customer, String animalToBuy)
    {
        boolean existAnimal = false;
        customer.setTimesOfVisit(customer.getTimesOfVisit() + 1);
        customer.setLatest(LocalDate.now());
        customerList.add(customer);
        for (Animal animal : animalList)
        {
            if (animal.getName().equals(animalToBuy))
            {
                existAnimal = true;
                savings += animal.price;

                System.out.printf("顾客" + customer.getName() + "买走了" + animalToBuy + "\r\n");
                System.out.printf(animal.toString() + "\r\n");
                animalList.remove(animal);
                break;
            }
        }
        if (!existAnimal)
        {
            throw new AnimalNotFoundException("没有这个动物qwq......");
        }
    }

    @Override
    public void close(LocalDate time)
    {
        if (LocalDate.now().equals(time))
        {
            System.out.println("歇业辣");
            System.out.println("当天光临的顾客有: ");
            for (Customer customer : customerList)
            {
                System.out.println(customer.toString());
            }
            System.out.println("今天赚的钱一共是: " + savings);
        }
    }

}

class AnimalNotFoundException extends RuntimeException
{
    public AnimalNotFoundException(String message)
    {
        super(message);
    }
}

class InsufficientBalanceException extends RuntimeException
{
    public InsufficientBalanceException(String message)
    {
        super(message);
    }
}

class Test
{

    public Test()
    {
        ArrayList<Animal> testAnimalList = new ArrayList<>();
        ArrayList<Customer> testCustomer = new ArrayList<>();
        MyAnimalShop mewAnimalShop = new MyAnimalShop(1234, testAnimalList, testCustomer);
        ChineseDog dog1 = new ChineseDog("狗狗1", 6, "公", 100);
        ChineseDog dog2 = new ChineseDog("狗狗2", 2, "母", 100);
        Cat cat1 = new Cat("猫猫1", 6, "母", 200);
        Cat cat2 = new Cat("猫猫2", 7, "母", 200);
        testAnimalList.add(dog1);
        testAnimalList.add(dog2);
        testAnimalList.add(cat1);
        testAnimalList.add(cat2);
        mewAnimalShop.buyANewAnimal(testAnimalList.get(0));
        mewAnimalShop.buyANewAnimal(testAnimalList.get(1));
        mewAnimalShop.buyANewAnimal(testAnimalList.get(2));
        mewAnimalShop.buyANewAnimal(testAnimalList.get(3));
        Customer customer1 = new Customer("超超", 0, null);
        Customer customer2 = new Customer("倪墨", 0, null);
        mewAnimalShop.pleaseTheCustomer(customer1, "猫猫1");
        mewAnimalShop.pleaseTheCustomer(customer1, "狗狗2");
        mewAnimalShop.pleaseTheCustomer(customer2, "猫猫2");
        mewAnimalShop.close(LocalDate.now());
    }
}