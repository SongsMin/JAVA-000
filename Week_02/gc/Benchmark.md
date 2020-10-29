| -Xmx(M) | 256           | 512           | 1024          |
| ------- | ------------- | ------------- | ------------- |
| 串行GC  | 3339rps / 5ms | 3094rps / 5ms | 2510rps / 8ms |
| 并行GC  | 2719rps / 6ms | 2435rps / 9ms | 3030rps / 5ms |
| CMS GC  | 3537rps / 4ms | 3361rps / 4ms | 3478rps / 4ms |
| G1 GC   | 3532rps / 4ms | 3452rps / 4ms | 3526rps / 4ms |

各种堆内存设置下的 Requests per second 及 tp99

