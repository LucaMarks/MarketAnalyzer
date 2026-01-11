import yfinance as yf

# namesOfStock = ["MSFT", "AAPL", "NVDA"]
namesOfStock = ["KO", "PEP"]
# 1d, 2y
interval = "1h"
period = "1y"

fileNames = []

for i in range(len(namesOfStock)):
    df = yf.download(
        tickers = namesOfStock[i],
        interval = interval,
        period = period,
        progress = False
    )
    fileName = f"{namesOfStock[i]}_data.csv"
    df.to_csv(fileName)
    fileNames.append(fileName)
    #add the interval & period as well
intervalPeriod = interval + " " + period
fileNames.append(intervalPeriod)

import subprocess

subprocess.run([
    "java",
    "-jar",
    "MarketAnalyzer.jar",
    ] + fileNames)

# nameOfStock = "MSFT"

# df = yf.download(
#     tickers=nameOfStock,
#     interval= interval,
#     period="1d",
#     progress=False
# )

# print(df)
# fileName = f"{nameOfStock}_data.csv"
# df.to_csv(fileName)

# import subprocess

# subprocess.run([
#     "java",
#     "-jar",
#     "MarketAnalyzer.jar",
#     fileName
# ])

