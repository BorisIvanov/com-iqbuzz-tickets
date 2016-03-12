Необходимо разработать систему бронирование билетов в кинотеатре.  
Есть возможность бронирования билета не более чем на один час (для бронирования нужно оставить свое ФИО).  
Если это время прошло и билет не выкуплен, он поступает снова в продажу.  
За 30 минут до начала сеанса все забронированные не выкупленные билеты также поступают заново в продажу.  
В зале есть места разной стоимости.  
В последнем ряду можно заказывать только по два места рядом.  
Карту зала можно задавать в файле, либо в таблице базы данных.  
Условия:  
Необходимо реализовать single page application с использованием js библиотек или фреймворков.  
В качестве визуального оформление рекомендовано использовать  css bootstrap.  
Обмен данным с бекендом должен быть реализован по протоколу JSON RPC  
Будет плюсом подключить к проекту gulp с минимизацией и сборкой ресурсов.  
В качестве сборки проекта, рекомендовано использовать maven.  
  
  
Решение
1. Билеты продаются на текущий день, этого достаточно для проверки реализации бронирования
2. Бронь можно оплатить только полностью
3. Все ряды имеют четное количество мест. Потому что последний ряд продается только парами, а для других рядов это не имеет значения.  
Для простоты считаем всt ряды имеют одинаковое количество мест.
