# [sample-htmx-rife2][repo]

Sample [rife2][rife2]/[htmx][htmx] todo app

## Requirements

- java 17
- htmx 1.9
- rife2's [bld][bld] build system

## Project setup

```bash
bld create-rife2 sample.htmx todoapp
mv todoapp sample-htmx-rife2
cd sample-htmx-rife2
# that's it, ready for coding a web service
```

## How to run

```bash
./bld clean download compile run
```

Or:

```bash
./bld clean download compile uberjar
java -jar build/dist/todoapp-0.1.0-uber.jar
```

## How to test

```bash
./bld clean download compile test
```

## Noteworthy

- Rife2 is quite complete when compared to other api frameworks, in java and
  other languages/platforms out there.
- The framework it's quite opinionated, and i am still trying to figure out the 
  best idiom to perform things.
- Bld is fantastic, fastest java build i've seen so far.
- Like previous experiments using htmx, simplicity is promising but looking
  through several implementations of the "same thing"(TM), proper template
  construction needs a shift from the component-ish to a pipeline-ish approach.
  distinct operations might end up having to deliver similar output and endpoint
  terminators should be capable of composite operations to deliver final state
  properly built for the requester scenario.

## Small [k6][benchmark] benchmark:

10 virtual users, during 30 seconds, on the following hardware:

```bash
[sombriks@lucien ~]()$ inxi --cpu --memory
Memory:
  System RAM: total: 16 GiB available: 15.31 GiB used: 8.75 GiB (57.2%)
  Message: For most reliable report, use superuser + dmidecode.
  Array-1: capacity: 16 GiB slots: 8 modules: 8 EC: None
  Device-1: Controller0-ChannelA type: LPDDR4 size: 2 GiB speed: 4267 MT/s
  Device-2: Controller0-ChannelB type: LPDDR4 size: 2 GiB speed: 4267 MT/s
  Device-3: Controller0-ChannelC type: LPDDR4 size: 2 GiB speed: 4267 MT/s
  Device-4: Controller0-ChannelD type: LPDDR4 size: 2 GiB speed: 4267 MT/s
  Device-5: Controller1-ChannelA type: LPDDR4 size: 2 GiB speed: 4267 MT/s
  Device-6: Controller1-ChannelB type: LPDDR4 size: 2 GiB speed: 4267 MT/s
  Device-7: Controller1-ChannelC type: LPDDR4 size: 2 GiB speed: 4267 MT/s
  Device-8: Controller1-ChannelD type: LPDDR4 size: 2 GiB speed: 4267 MT/s
CPU:
  Info: 10-core (2-mt/8-st) model: 12th Gen Intel Core i7-1255U bits: 64
    type: MST AMCP cache: L2: 6.5 MiB
  Speed (MHz): avg: 550 min/max: 400/4700:3500 cores: 1: 1111 2: 400 3: 400
    4: 400 5: 400 6: 776 7: 630 8: 400 9: 400 10: 560 11: 503 12: 628
```

Results:

```bash
$ k6 run benchmark-javalin.js 

          /\      |‾‾| /‾‾/   /‾‾/   
     /\  /  \     |  |/  /   /  /    
    /  \/    \    |     (   /   ‾‾\  
   /          \   |  |\  \ |  (‾)  | 
  / __________ \  |__| \__\ \_____/ .io

     execution: local
        script: benchmark-javalin.js
        output: -

     scenarios: (100.00%) 1 scenario, 10 max VUs, 1m0s max duration (incl. graceful stop):
              * default: 10 looping VUs for 30s (gracefulStop: 30s)


     ✓ 200 ok

     checks.........................: 100.00% ✓ 53526       ✗ 0    
     data_received..................: 269 MB  8.9 MB/s
     data_sent......................: 4.3 MB  143 kB/s
     http_req_blocked...............: avg=10.85µs  min=1.81µs   med=8.87µs   max=5.82ms   p(90)=13.95µs p(95)=17.15µs
     http_req_connecting............: avg=56ns     min=0s       med=0s       max=579.88µs p(90)=0s      p(95)=0s     
     http_req_duration..............: avg=5.21ms   min=1.07ms   med=4.47ms   max=330.57ms p(90)=8.36ms  p(95)=10.36ms
       { expected_response:true }...: avg=5.21ms   min=1.07ms   med=4.47ms   max=330.57ms p(90)=8.36ms  p(95)=10.36ms
     http_req_failed................: 0.00%   ✓ 0           ✗ 53526
     http_req_receiving.............: avg=492.18µs min=36.79µs  med=251.22µs max=20.09ms  p(90)=1.08ms  p(95)=1.58ms 
     http_req_sending...............: avg=47.16µs  min=9.68µs   med=37.82µs  max=15.81ms  p(90)=65.01µs p(95)=82.28µs
     http_req_tls_handshaking.......: avg=0s       min=0s       med=0s       max=0s       p(90)=0s      p(95)=0s     
     http_req_waiting...............: avg=4.67ms   min=948.82µs med=3.97ms   max=312.58ms p(90)=7.59ms  p(95)=9.38ms 
     http_reqs......................: 53526   1784.008926/s
     iteration_duration.............: avg=5.55ms   min=1.3ms    med=4.81ms   max=331.44ms p(90)=8.76ms  p(95)=10.78ms
     iterations.....................: 53526   1784.008926/s
     vus............................: 10      min=10        max=10 
     vus_max........................: 10      min=10        max=10 


running (0m30.0s), 00/10 VUs, 53526 complete and 0 interrupted iterations
default ✓ [======================================] 10 VUs  30s
```

Faster than [this one][spring] (9552), [this one too][javalin] (20688),
[this][chi] (29358) and [this][koa] (7370) and only losing losing for
[this one][fiber] (112398).

Important to say, those numbers change a lot depending on the hardware: throw
more cores and better ssd and spring version get better.

## Further reading

- https://github.com/rife2/rife2/wiki
- https://github.com/rife2/bld/wiki
- https://www.h2database.com/html/quickstart.html
- https://htmx.org/docs/
- https://k6.io/docs/examples/

[repo]: https://github.com/sombriks/sample-htmx-rife2
[rife2]: https://rife2.com/
[htmx]: https://htmx.org/
[bld]: https://rife2.com/bld
[benchmark]: https://github.com/sombriks/node-vs-kotlin-k6-benchmark
[javalin]: https://github.com/sombriks/sample-htmx-javalin
[spring]: https://github.com/sombriks/sample-htmx-spring
[fiber]: https://github.com/sombriks/my-golang-handbook/tree/main/exercises/0015-rest-htmx
[chi]: https://github.com/sombriks/sample-htmx-chi
[koa]: https://github.com/sombriks/sample-htmx-koa
