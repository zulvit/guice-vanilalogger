# guice-vanilalogger
<b>Общие требования:</b>
Нужно создать консольное приложение, которое ожидает ввода строки и логирует введенное указанным способом. Это может быть один из трех вариантов: 
вывод в консоль, вывод в файл оба эти варианта сразу (композитное), сначала в консоль, а потом в файл. 

Вид логирования задается параметром при запуске консольного приложения. Логирование в файл заключает строку в тэг, например, так: <a>input</a>. Название тэга также указывается как параметр при запуске приложения. 

Каждое логирование сопровождается уникальным порядковым номером.Так, если выбрано композитное логирование, то введенная строка выводится в консоль с номером N и в файл с номером N + 1.

<h3>Пример композитного логирования:</h3>
<b>CONSOLE:</b>
<p>input: Hey!</p>
<p>output: 1. <a>Hey!</a></p>
<p>input: Wow. Logger working =)</p>
<p>output: 3. <a>Wow. Logger working =)</a></p>
<p>input: We have a custom tag.</p>
<p>output: 5. <a>We have a custom tag.</a></p>
<p>input: To day is a good day</p>
<p>output: 7. <a>To day is a good day</a></p>
<p>input: println("Hello VK")</p>
<p>output: 9. <a>println("Hello VK")</a></p>

<b>LogFile.log</b>
<p>0. &lta&gtHey!&lta&gt</p>
<p>2. &lta&gtWow. Logger working =)&lta&gt</p>
<p>4. &lta&gtWe have a custom tag.&lta&gt</p>
<p>6. &lta&gtTo day is a good day&lta&gt</p>
<p>8. &lta&gtprintln("Hello VK")&lta&gt</p>
