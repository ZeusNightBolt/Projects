"""CSC 161 Project: Milestone II

Delusion Thakkar
Lab Section 3:25-4:40pm
Fall 2020
"""


def opend_file(filename):

    f = open(filename, "r")
    z = f.readline()
    flines = f.readlines()
    return flines


def parsed_data(flines):

    Prices = []
    for pricedata in flines:

        splitter = pricedata.split(',')
        Prices.append(float(splitter[4]))         # I prefer to use close price

    return Prices


def open_file(filename):

    f = open(filename, "r")
    flines = f.readlines()
    return flines


def parse_data(flines):

    fliness = []
    flinesss = []

    for i in range(1, len(flines)):

        fliness.append(flines[i].split(','))

    zz = [i.split(',', 1)[0] for i in flines]

    return fliness


def test_data(filename, col, day):

    """A test function to query the data you loaded into your program.

    Args:
        filename: A string for the filename containing the stock data,
                  in CSV format.

        col: A string of either "date", "open", "high", "low", "close",
             "volume", or "adj_close" for the column of stock market data to
             look into.

             The string arguments MUST be LOWERCASE!

        day: An integer reflecting the absolute number of the day in the
             data to look up, e.g. day 1, 15, or 1200 is row 1, 15, or 1200
             in the file.

    Returns:
        A value selected for the stock on some particular day, in some
        column col. The returned value *must* be of the appropriate type,
        such as float, int or str.
    """

    filename = str(filename)
    thelines = open_file(filename)
    parsed = parse_data(thelines)
    # 1, 8, 15 is open values. 2, 9, 16 are high values
    # we just use this and don't play the data too much
    # if day = 2 then return 8 and similarly
    temp = 1
    if(col == "date"):
        temp = 0
    elif(col == "open"):
        temp = 1
    elif(col == "high"):
        temp = 2
    elif(col == "low"):
        temp = 3
    elif(col == "close"):
        temp = 4
    elif(col == "adj_close"):
        temp = 5
    elif(col == "volume"):
        temp = 6
    else:
        temp = 1

    if temp == 0:
        return ((parsed[int(day)-1][temp]))
    if temp == 6:
        return (int(parsed[int(day)-1][temp]))
    else:
        return (float(parsed[int(day)-1][temp]))


def transact(funds, stocks, qty, price, buy=False, sell=False):

    """A bookkeeping function to help make stock transactions.

       Args:
           funds: An account balance, a float; it is a value of how much money
           you have,
                  currently.

           stocks: An int, representing the number of stock you currently own.

           qty: An int, representing how many stock you wish to buy or sell.

           price: An float reflecting a price of a single stock.

           buy: This option parameter, if set to true, will initiate a buy.

           sell: This option parameter, if set to true, will initiate a sell.

       Returns:
           Two values *must* be returned. The first (a float) is the new
           account balance (funds) as the transaction is completed. The second
           is the number of stock now owned (an int) after the transaction is
           complete.

           Error condition #1: If the `buy` and `sell` keyword parameters
           are both set to true, or both false. You *must* raise an
           ValueError exception with an appropriate error message since this
           is an ambiguous transaction.

           Error condition #2: If you buy, or sell without enough funds or
           stocks to sell, respectively.  You *must* raise an
           ValueError exception with an appropriate error message since this
           is an ambiguous transaction.
    """

    if(buy is False and sell is False):
        # the 'raise' keyword is followed by the instance of exception type 
        # (ValueError) which is
        # given a custom description ("Ambiguous transaction...")
        raise ValueError("Ambiguous transaction! Can't determine whether to buy or sell!")

    if(buy is True and sell is True):
        # the 'raise' keyword is followed by the instance of exception type 
        # (ValueError) which is
        # given a custom description ("Ambiguous transaction...")
        raise ValueError("Ambiguous transaction! Can't determine whether to buy or sell!")

    if(buy is True and sell is False):
        if(float(funds) < float(qty*price)):
            # the 'raise' keyword is followed by the instance of exception type
            # (ValueError) which is
            # given a custom description ("Ambiguous transaction...")
            raise ValueError(f"Insufficient funds to purchase {qty} stock at ${price:0.2f}!")

        else:
            stocks = int(qty + stocks)
            funds = float(funds - (price*qty))
            return funds, stocks

    if(buy is False and sell is True):
        if(stocks < qty):
            raise ValueError(f"Insufficient stock owned to sell {qty} stocks!")
        else:
            stocks = int(stocks - qty)
            funds = float(funds + (price*qty))
            return funds, stocks


def alg_moving_average(filename):
    """This function implements the moving average stock trading algorithm.

    The CSV stock data should be loaded into your program; use that data to
    make decisions using the moving average algorithm.

    Any bookkeeping setup from Milestone I should be called/used here.

    Algorithm:
    - Trading must start on day 21, taking the average of the previous 20 days.
    - You must buy shares if the current day price is 5%, or more, lower
      than the moving average.
    - You must sell shares if the current day price is 5%, or more, higher,
      than the moving average.
    - You must buy, or sell 10 stocks, or less per transaction.
    - You are free to choose which column of stock data to use (open, close,
      low, high, etc)
    - When your algorithm reaches the last day of data, have it sell all
      remaining stock. Your function will return the number of stocks you
      own (should be zero, at this point), and your cash balance.
    - Choose any stock price column you wish for a particular day you use
      (whether it's the current day's "open", "close", "high", etc)

    Args:
        A filename, as a string.

    Returns:
        Note: You *must* sell all your stock before returning.
        Two values, stocks and balance OF THE APPROPRIATE DATA TYPE.

    Prints:
        Nothing.
    """

    maValue = 0
    buyBool = False
    sellBool = False
    zz = opend_file(filename)
    zzz = parsed_data(zz)
    counter = 0
    stocks_owned = 0
    cash_balance = float(1000.0)

    for z in zzz:

        maValue = maValue + z
        counter = counter + 1
        if(counter > 20):
            break

    maValueFinal = maValue / counter

    if(zzz[20] < (0.95*maValueFinal)):

        buyBool = True
        transact(cash_balance, stocks_owned, 5, zzz[20], buy=buyBool,
                 sell=sellBool)
    elif(zzz[20] > (1.05*maValueFinal)):

        sellBool = True
        transact(cash_balance, stocks_owned, 1, zzz[20], buy=buyBool,
                 sell=sellBool)

    for i in range(20, len(zzz)):

        buyBool = False
        sellBool = False
        maValue = maValue - zzz[(i-20)] + zzz[i]
        maValueFinal = maValue / 20
        if(zzz[i] < (0.95*maValueFinal)):
            buyBool = True
            transact(cash_balance, stocks_owned, 5, zzz[i], buy=buyBool,
                     sell=sellBool)
        elif(zzz[i] > (1.05*maValueFinal)):
            sellBool = True
            transact(cash_balance, stocks_owned, 1, zzz[i], buy=buyBool,
                     sell=sellBool)

        if(i == (len(zzz) - 1)):

            transact(cash_balance, stocks_owned, stocks_owned, zzz[i],
                     sell=True)

    # Last thing to do, return two values: one for the number of stocks you
    # end up
    # owning after the simulation, and the amount of money you have after
    #  the simulation.
    # Remember, all your stocks should be sold at the end!
    return stocks_owned, cash_balance


def main():
    # My testing will use AAPL.csv or MSFT.csv
    filename = input("Enter a filename for stock data (CSV format): ")

    # Call your moving average algorithm, with the filename to open.
    alg1_stocks, alg1_balance = alg_moving_average(filename)

    # Print results of the moving average algorithm, returned above:
    print("The results are... {0:} {1:}".format(alg1_stocks, alg1_balance))


if __name__ == '__main__':
    main()
    # test = test_data("AAPL (1).csv", "open", 2)
    # print(test)
    # print(type(test))
    # cash_balance = 1000
    # stocks_owned = 25
    # cash_balance, stocks_owned = transact(cash_balance, stocks_owned,
    # 3, test, buy=True)
    # print(cash_balance)
    # print(stocks_owned)
