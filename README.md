# Java Microbenchmarks

## List Iteration


100K elements

|Benchmark | Mode | Cnt | Score | Error | Units |
|---|---|---|---|---|---|
|ListIteration.forEach  | thrpt  | 20  | 5969.976 | ±  24.819 | ops/s |
ListIteration.indexed | thrpt  | 20 | 6004.061 | ±  32.902 | ops/s |
ListIteration.lambda  | thrpt  | 20 | 1832.344 | ± 127.956 | ops/s |


1M elements

|Benchmark|Mode|Cnt|Score|Error|Units |
|---|---|---|---|---|---|
|ListIteration.forEach|thrpt|20|100.219|± 8.667|ops/s|
|ListIteration.indexed|thrpt|20|90.765|± 4.240|ops/s|
|ListIteration.lambda|thrpt|20|176.831|± 9.308|ops/s|
        