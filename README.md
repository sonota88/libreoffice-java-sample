JavaでLibreOffice Calcのfodsファイルを読み書きするサンプル 2021  
https://memo88.hatenablog.com/entry/2021/01/01/155345

JavaでLibreOffice Calcのfodsファイルを読み書きするサンプル 2019  
https://memo88.hatenablog.com/entry/2019/12/02/070925

----

セットアップ

    ./build.sh setup

パッケージ（jar）作成

    ./build.sh package

ホストで実行

    ./run.sh

Docker イメージをビルド

    ./build.sh docker-image

Docker コンテナ内で実行

    docker run --rm \
      -v ${PWD}:/root/work \
      libo-sample:test \
      /root/work/run.sh
