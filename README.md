Docker イメージをビルド

    ./build.sh docker-image

パッケージ（jar）作成

    ./build.sh package

ホストで実行

    ./run.sh

Docker コンテナ内で実行

    docker run --rm \
      -v ${PWD}:/root/work \
      libo-sample:test \
      /root/work/run.sh
