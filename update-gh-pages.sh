#!/usr/bin/env bash
sbt fullOptJS

TMPDIR=$(mktemp -d)
cp target/scala-2.11/classes/index.html $TMPDIR/
cp target/scala-2.11/scalapb-on-scalajs-demo-opt.js $TMPDIR/
git checkout gh-pages
cp -rvT $TMPDIR .
git add index.html scalapb-on-scalajs-demo-opt.js
git commit -m"Update static site."
git checkout master

