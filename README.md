# Bolonha e Milano

Jogo de estratégia económica medieval em Java (linha de comando), para 1 a 4 jogadores.
Cada jogador governa uma cidade-Estado (Bolonha, Milano, Florença, Veneza) e gere agricultura,
impostos, construções e defesa para subir de título. Projeto da disciplina SPOTDES (IFSP).

## Regras resumidas
- O jogo começa em **1400** e termina em **1410**, ou quando alguém se torna **Rei/Rainha**.
- Cada jogador joga o seu turno; quando o último encerra, o ano fecha e os títulos são avaliados.
- **Colher** e **arrecadar impostos** valem uma vez por ano por cidade.
- Prestígio = construções × 10 + soldados × 2 + servos + terras ÷ 100 (florins não contam).
- Títulos (prestígio mínimo): Cavaleiro (início), Barão 400, Conde 550, Marquês 700, Duque 850, Rei/Rainha 1000.
  No máximo um título por ano.
- Vencedor: quem virar Rei/Rainha primeiro; senão, o título mais alto em 1410 (empate: maior prestígio).

## Como compilar
Na raiz do repositório:

```bash
mkdir bin
javac -encoding UTF-8 -d bin src/bolonhaemilano/*.java
```

## Como jogar
```bash
java -cp bin bolonhaemilano.Main
```

| Opção | O que faz |
|-------|-----------|
| 1 | Novo jogo (pede 1 a 4 jogadores, nomes e cidades) |
| 2 | Ver status da cidade da vez |
| 3 | Mercado: comprar/vender grãos e terras |
| 4 | Agricultura: colher (1x/ano) e distribuir alimentos (20% a 80%) |
| 5 | Governo: arrecadar impostos (1x/ano), taxa (0 a 50%) e justiça (Justa/Moderada/Severa) |
| 6 | Construções: recrutar soldados (5 florins + 1 servo) e construir (20 florins) |
| 7 | Encerrar turno |
| 8 | Ver classificação |
| 9 | Sobre o jogo |
| 0 | Sair |

## Como rodar os testes
```bash
java -cp bin bolonhaemilano.Testes
```
Saída esperada no final: `Aprovados: 37 | Reprovados: 0`.
Se algum teste falhar, o programa termina com código 1.

## Estrutura
```
src/bolonhaemilano/   código-fonte (Main, Menu, Jogador, Cidade, Mercado, Agricultura,
                      Governo, Construcoes, Nobreza, Partida, Testes)
uml/                  diagramas de classes e de sequência
casos-de-uso/         diagrama de casos de uso
```
Os diagramas estão em PlantUML (`.puml`); podem ser renderizados em plantuml.com ou numa extensão do VS Code.
