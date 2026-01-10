import yfinance as yf

namesOfStock = ["MSFT", "AAPL", "NVDA"]
interval = "5m"
period = "1d"

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

