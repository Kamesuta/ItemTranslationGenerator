# QuickShopのアイテム翻訳ツール

QuickShopの翻訳ファイル `itemi18n.yml`, `enchi18n.yml`, `potioni18n.yml` を翻訳するためのツールです。

## 使い方

1. `plugins/ItemTranslationGenerator/in` に `itemi18n.yml`, `enchi18n.yml`, `potioni18n.yml` をコピーします。
2. `plugins/ItemTranslationGenerator` にMinecraftからコピーした翻訳ファイル(`ja_jp.json`など) を `translation.json` にリネームしてコピーします。
3. プラグインを入れてサーバーを起動します。
4. `plugins/ItemTranslationGenerator/out` に翻訳ファイルが生成されます。
5. 生成されたファイルをQuickShopの `plugins/QuickShop` にコピーします。