[![aop with Maven](https://github.com/AlekseiPetrovJ/aop/actions/workflows/maven.yml/badge.svg)](https://github.com/AlekseiPetrovJ/aop/actions/workflows/maven.yml)
# ������� ����� ������� ���������� �������

������� ����� ������� ���������� ������� � ���������� � �������������� Spring AOP.
������� ��������� ���������� ���������� � ������������� ������ � ������� ���������� �������.
��� ������������� ���������� � ����������� ������ � ���� �������������� ����� ����������.
���� ����������� � �� postgresql.
���������� REST ���������� ��� ��������� �����.
�������� CI-�������� � git �� ������.
���������� ����� ��� ��������� ���������� (�������, �����������, ������������ ����� ����������, ���������� �������)

## [��������� ����������� �������](OpenSchoolDZ.txt)

# ������ ����������
## ����������
������������� maven, docker, docker compose

## ������
1) �������� ������ �� ����� master
2) � ��������� ������ (cmd/bash) ��������� � ������� �������

windows: `cd C:\Users\user\Downloads\<��������������>`

linux: `cd ~/Downloads/<��������������>`

3) �������� ����� �����:

windows: `mvnw.cmd -B clean package dockerfile:build`

linux: `./mvnw -B clean package dockerfile:build`

4) ������ �������:

`docker compose up
`
## OpenAPI
[AOP](http://127.0.0.1:8080/swagger-ui/index.html)