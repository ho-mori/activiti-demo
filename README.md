# activiti-demo

Java 21 + Spring Boot 3 + Gradle 8.7 + Activiti 8 で作る BPMN ワークフロープロジェクトの README と `.gitignore` です。

---

## README.md

### 📖 概要

このプロジェクトは **Spring Boot 3.3** と **Activiti 8.2.x** を用いて、BPMN プロセス（ユーザータスクを含む）を実行する最小構成のサンプルです。Gradle 8.7 の  Wrapper を同梱し、Java 21 を前提にしています。

### 🚀 クイックスタート

```bash
# 1. リポジトリを取得
$ git clone https://github.com/your-org/activiti-demo.git
$ cd activiti-demo

# 2. Gradle Wrapper でビルド & 実行
$ ./gradlew bootRun
```

起動すると `helloUser` プロセスが自動的に開始し、コンソールにタスク完了メッセージが表示されます。
サンプルでは簡易的なユーザーとして `user/password` を登録しています。
Activiti 8 は Spring Security を利用するため、必要に応じて `SecurityConfig` クラスでユーザーを追加・変更してください。


### 実行方法

以下のコマンドでアプリを起動できます。
```bash
./gradlew bootRun
```
JAR を生成して実行する場合は次の通りです。
```bash
./gradlew bootJar
java -jar build/libs/activiti-demo-0.0.1-SNAPSHOT.jar
```

### 🔄 BPMN を動的にリロードする

`/workflow/reload` エンドポイントでは `workflow.bpmn-path` プロパティで指定された
外部 BPMN ファイルを読み込みます。デフォルト値は
`src/main/resources/processes/hello-user.bpmn20.xml` です。ファイルを編集してから
再度 `/workflow/reload` を呼び出すと変更が反映されます。

### 🗂️ ディレクトリ構成

```
activiti-demo/
├─ src/
│  ├─ main/java/com/example/WorkflowApplication.java  # Spring Boot エントリ
│  └─ main/resources/processes/hello-user.bpmn20.xml  # BPMN 定義
├─ build.gradle                                       # Gradle 設定
├─ settings.gradle                                    # プロジェクト名定義
├─ docker-compose.yml                                 # PostgreSQL（任意）
└─ README.md                                          # 本ファイル
```

### 🛠️ 前提ソフトウェア

| ツール | バージョン              | インストール確認   |
| ------ | ----------------------- | ------------------ |
| JDK    | 21.x                    | `java -version`    |
| Git    | 2.40+                   | `git --version`    |
| Gradle | **不要** (Wrapper 使用) | -                  |
| Docker | 最新                    | `docker --version` |

### 📝 主な Gradle タスク

| コマンド                   | 説明                       |
| -------------------------- | -------------------------- |
| `./gradlew bootRun`        | 開発用にアプリを起動       |
| `./gradlew test`           | 単体テスト実行             |
| `./gradlew bootJar`        | 実行可能 JAR を生成        |
| `./gradlew bootBuildImage` | OCI コンテナイメージを作成 |

### 🐘 Activiti Explorer (任意)

```bash
$ docker run --rm -p 8081:8080 alfresco/activiti-explorer:8.2.0-alpha.21
```

[http://localhost:8081](http://localhost:8081) にアクセスして、BPMN プロセスやタスクを GUI で確認できます。

### 🐳 PostgreSQL を使う

```bash
$ docker compose up -d db
```

`application.yml` では `datasource` セクションがコメントアウトされており、デフォルトではインメモリデータベースを利用します。PostgreSQL を使う場合はこのセクションを有効にしてください。

### 📜 ライセンス

MIT License（詳細は `LICENSE` ファイルを参照）。

---

## .gitignore

以下を `activiti-demo/.gitignore` として保存してください。
