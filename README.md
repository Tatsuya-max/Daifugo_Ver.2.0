# Daifugo_Ver.2.0
コーディングの勉強をかねて作成したトランプゲーム「大富豪」のコンソールアプリ。

- プレイヤー、CPUの行動選択を決める
 - PlayerMove.java
 - AIClass.java
 - HandEvaluator.java
 などが未実装のため、メインの DaifugoSimulation.java は動作しない。

- ゲーム開始後の基本の流れは Game.java に記述されている。

- Deck.java実行
1. ジョーカー抜きのトランプ一式を生成
2. シャッフル
3. プレイヤー人数に応じ、端数になるカードを消却
4. ジョーカーを2枚追加
5. シャッフル
シャッフルは、カジノディーラが実際に行っているものを再現した。
「リフルシャッフル → ストリップカット → リフルシャッフル」
