# xml-uploading
xml-uploading and parsing

xsd-файл внутри проекта

Реализовать веб приложение c помощью Spring:
1. Веб форма, которая на вход получает xml файл вид:
	<customers>
		...
		<customer>
			<id>233658</id>
			<name>Игорь Владимирович</name>
			<orders>
				...
				<order>
					<id>233658</id>
					<positions>
						...
						<position>
							<id>233658</id>
							<price>30.0</price>
							<count>5</count>
						</position>
						...
					</positions>
				</order>
				...
			</orders>
		</customer>
		...
	</customers>
2. Написать xsd схему по данному xml
3. После загрузки xml на форму на сервере должно пройти расчеты и вывести:
	3.1 Сумму всех заказов	
	3.2 Клиента с максимальной суммой заказов
	3.3 Сумму максимального заказа
	3.4 Сумму минимального заказа
	3.5 Количество заказов
	3.6 Средняя сумма заказа
4*. Реализовать многопоточный парсер xml 
5*. Вывести клиентов с суммой по заказам больше N, где N настраиваемое число (поле в форме)

* - по желанию

sample xml file
//----------------------------------------------------------
<?xml version="1.0" encoding="utf-8"?>
<customers>
  <customer>
    <id>124</id>
    <name>Виталий Владимирович</name>
    <orders>
      <order>
        <id>1241</id>
        <positions>
          <position>
            <id>12411</id>
            <price>3.14159</price>
            <count>1</count>
          </position>
        </positions>
      </order>
    </orders>
  </customer>
  <customer>
    <id>123</id>
    <name>Алексей Петрович</name>
    <orders>
      <order>
        <id>1231</id>
        <positions>
          <position>
            <id>12311</id>
            <price>3.89</price>
            <count>19</count>
          </position>
        </positions>
      </order>
	  <order>
        <id>1232</id>
        <positions>
          <position>
            <id>12321</id>
            <price>66.3</price>
            <count>1</count>
          </position>
		  <position>
            <id>12322</id>
            <price>6.3</price>
            <count>9</count>
          </position>
        </positions>
      </order>
	  <order>
        <id>1233</id>
        <positions>
          <position>
            <id>12331</id>
            <price>66.3</price>
            <count>16</count>
          </position>
        </positions>
      </order>
    </orders>
  </customer>

</customers>
//----------------------------------------------------------
Выплнены пункты 1-3.
