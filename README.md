# Socket

## Docker
```bash
docker build --platform linux/amd64 -t eorjs37/socket:0.1 . 
```

```bash
docker run -p 9090:9090 -d eorjs37/socket:0.1 
```

```bash
docker push eorjs37/socket:0.1
```

```bash
docker stop [containerid]
```

```bash
docker container rm [containerid]
```
```bash
docker run -p 9090:9090 -d --name spring eorjs37/socket:0.1
```
```bash
docker pull eorjs37/socket:0.1
```