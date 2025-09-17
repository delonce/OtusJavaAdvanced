Обоснование обнаружения места утечки и описание скриншотов.

1. Видим утечку памяти в хипе через VisualVM, делаем дамп и анализируем его через MAT.
2. Автоматический отчёт MAT показывает, что какая-то мапа заняла 83% всего хипа, что указывает на утечку.
3. В этом же автоматическом отчёте видим путь, по которому создана подозрительная мапа, а также упоминание removalCause
4. В dominator tree при анализе вручную видим ту же мапу, которая очевидно выделяется по своему размеру на фоне остальных объектов.
5. Смотрим пути до GC Roots подозрительной мапы и обнаруживаем ссылку logDeletedRecords, которая и является источником проблемы.

Наблюдения о производительности запуска Native Image под GraalVM v22.0.2

Запуск без Native Image: 
```
2025-09-08T17:16:04.294+03:00  INFO 19208 --- [signIn] [           main] org.delonce.signIn.SignInApplication     : Started SignInApplication in 5.249 seconds (process running for 6.171)
```

Запуск Native Image в докере:
```
2025-09-09T06:44:28.404Z  INFO 7 --- [signIn] [           main] org.delonce.signIn.SignInApplication     : Started SignInApplication in 0.205 seconds (process running for 0.208)   
```

Результаты бенчмарка:
```
Benchmark                                                              Mode     Cnt        Score    Error  Units
signIn.security.Md5EncoderBenchmarks.testPasswordEncoder              thrpt       2  4056837,151           ops/s
signIn.security.Sha256EncoderBenchmarks.testPasswordEncoder           thrpt       2  1641861,628           ops/s
signIn.security.Sha512EncoderBenchmarks.testPasswordEncoder           thrpt       2  1830349,328           ops/s
signIn.security.Md5EncoderBenchmarks.testPasswordEncoder               avgt       2       ? 10??            s/op
signIn.security.Sha256EncoderBenchmarks.testPasswordEncoder            avgt       2       ? 10??            s/op
signIn.security.Sha512EncoderBenchmarks.testPasswordEncoder            avgt       2       ? 10??            s/op
signIn.security.Md5EncoderBenchmarks.testPasswordEncoder             sample  607102       ? 10??            s/op
signIn.security.Md5EncoderBenchmarks.testPasswordEncoder:p0.00       sample               ? 10??            s/op
signIn.security.Md5EncoderBenchmarks.testPasswordEncoder:p0.50       sample               ? 10??            s/op
signIn.security.Md5EncoderBenchmarks.testPasswordEncoder:p0.90       sample               ? 10??            s/op
signIn.security.Md5EncoderBenchmarks.testPasswordEncoder:p0.95       sample               ? 10??            s/op
signIn.security.Md5EncoderBenchmarks.testPasswordEncoder:p0.99       sample               ? 10??            s/op
signIn.security.Md5EncoderBenchmarks.testPasswordEncoder:p0.999      sample               ? 10??            s/op
signIn.security.Md5EncoderBenchmarks.testPasswordEncoder:p0.9999     sample               ? 10??            s/op
signIn.security.Md5EncoderBenchmarks.testPasswordEncoder:p1.00       sample                0,002            s/op
signIn.security.Sha256EncoderBenchmarks.testPasswordEncoder          sample  565001       ? 10??            s/op
signIn.security.Sha256EncoderBenchmarks.testPasswordEncoder:p0.00    sample               ? 10??            s/op
signIn.security.Sha256EncoderBenchmarks.testPasswordEncoder:p0.50    sample               ? 10??            s/op
signIn.security.Sha256EncoderBenchmarks.testPasswordEncoder:p0.90    sample               ? 10??            s/op
signIn.security.Sha256EncoderBenchmarks.testPasswordEncoder:p0.95    sample               ? 10??            s/op
signIn.security.Sha256EncoderBenchmarks.testPasswordEncoder:p0.99    sample               ? 10??            s/op
signIn.security.Sha256EncoderBenchmarks.testPasswordEncoder:p0.999   sample               ? 10??            s/op
signIn.security.Sha256EncoderBenchmarks.testPasswordEncoder:p0.9999  sample               ? 10??            s/op
signIn.security.Sha256EncoderBenchmarks.testPasswordEncoder:p1.00    sample                0,001            s/op
signIn.security.Sha512EncoderBenchmarks.testPasswordEncoder          sample  649055       ? 10??            s/op
signIn.security.Sha512EncoderBenchmarks.testPasswordEncoder:p0.00    sample               ? 10??            s/op
signIn.security.Sha512EncoderBenchmarks.testPasswordEncoder:p0.50    sample               ? 10??            s/op
signIn.security.Sha512EncoderBenchmarks.testPasswordEncoder:p0.90    sample               ? 10??            s/op
signIn.security.Sha512EncoderBenchmarks.testPasswordEncoder:p0.95    sample               ? 10??            s/op
signIn.security.Sha512EncoderBenchmarks.testPasswordEncoder:p0.99    sample               ? 10??            s/op
signIn.security.Sha512EncoderBenchmarks.testPasswordEncoder:p0.999   sample               ? 10??            s/op
signIn.security.Sha512EncoderBenchmarks.testPasswordEncoder:p0.9999  sample               ? 10??            s/op
signIn.security.Sha512EncoderBenchmarks.testPasswordEncoder:p1.00    sample                0,001            s/op
signIn.security.Md5EncoderBenchmarks.testPasswordEncoder                 ss       2       ? 10??            s/op
signIn.security.Sha256EncoderBenchmarks.testPasswordEncoder              ss       2       ? 10??            s/op
signIn.security.Sha512EncoderBenchmarks.testPasswordEncoder              ss       2       ? 10??            s/op
```