package tests;

import hooks.Hooks;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.*;

public class RunTestCases extends Hooks {


    String expectedValue, actualValue;

    @Test
    //Проверить корректный порядок выполнения операций
    //ТРЕБУЕТСЯ УТОЧНЕНИЕ: узнать правильный порядок выполнения операций
    public void checkCorrectOrder() {
        expectedValue = "6";
        actualValue = performCalculation("+2", "+2", "-1", "*2" );
        assertEquals(expectedValue, actualValue);
    }

    @Test
    //Сложение двух чисел больше нуля
    public void additionOfTwoPositiveIntegers() {
        expectedValue = "4";
        actualValue = performCalculation("+2", "+2");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    //Сложение положительного и отрицательного числа
    public void additionOfPositiveAndNegativeNumbers() {
        expectedValue = "0";
        actualValue = performCalculation("+2", "+-2");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    //Вычитание из положительного числа
    public void subtractTwoIntegers() {
        expectedValue = "0";
        actualValue = performCalculation("+2", "-2");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    //Вычитание двух отрицательных чисел
    public void subtractTwoNegativeIntegers() {
        expectedValue = "-4";
        actualValue = performCalculation("-2", "-2");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    //Умножение отрицательного числа на положительное
    public void multiplicationPositiveToNegative() {
        expectedValue = "-4";
        actualValue = performCalculation("-2", "*2");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    //Умножение двух отрицательных чисел
    public void multiplicationNegativeToNegative() {
        expectedValue = "4";
        actualValue = performCalculation("-2", "*-2");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    //Умножение на ноль
    public void checkMultiplicationToZero() {
        expectedValue = "0";
        actualValue = performCalculation("+2", "+2", "*0");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    //Проверка минус на минус равно плюс
    public void checkTwoMinusesEqualsPlus(){
        expectedValue = "4";
        actualValue = performCalculation("+2", "--2");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    //Проверка минус на минус равно плюс
    public void checkHugeNumbersAddition(){
        expectedValue = "2147483648";
        actualValue = performCalculation("+2147483647", "+1");
        assertEquals(expectedValue, actualValue);
    }



    private String performCalculation(String... values) {
        String baseUrl = "http://localhost:8080/calc/";
        String resultXPath = "/html/body/pre";
        WebElement result;
        StringBuilder finalUrl = new StringBuilder(baseUrl);

        for (String value: values) {
            finalUrl.append(value).append('/');
        }

        driver.get(String.valueOf(finalUrl));
        result = driver.findElement(By.xpath(resultXPath));
        return result.getText();
    }
}
