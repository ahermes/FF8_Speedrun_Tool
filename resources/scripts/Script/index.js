const { Select } = require("enquirer");
const { AutoComplete } = require("enquirer");
const { NumberPrompt } = require("enquirer");
const tablesData = require("./table.json");
const RNGData = require("./RNGMap.json");
const MinCrisis = 5;
function calculCrisis(limitLevel){
    if (limitLevel <= 4) return 0;
    if (limitLevel === 5) return 1;
    if (limitLevel === 6) return 2;
    if (limitLevel === 7) return 3;
    return 4;
}

function calculPossibleRNG() {
    let data = JSON.parse(process.argv[process.argv.length-1].replaceAll('\'', "\""));
    process.argv.forEach(function (val, index, array) {
        console.log(index + ': ' + val);
    });
    console.log(data);
    const Min = 415;
    const Max = 160;
    let RandomMod = Min;
    let LimitLevel = 255;
    let HPMod = Math.floor((2500*data.CurrentHp) / data.MaxHp);
    let DeathBonus = Math.floor((200 * data.DeadCharacters) + 1600);
    let StatusBonus = Math.floor(10 * data.StatusSum);
    console.log(HPMod)
    console.log(DeathBonus)
    console.log(StatusBonus)
    let RNG = [];
    let toto = [];
    while (RandomMod >= Max) {
        let crisis =  Math.floor((StatusBonus + DeathBonus - HPMod) / RandomMod);
        if (crisis >= MinCrisis) {

//            RNG.push([LimitLevel, ]);
            let currentCrisis = calculCrisis(crisis);
            let possibleRNGData = RNGData.filter(rng => rng.limitLevel === LimitLevel)
            .map((row) => {
                  return {
                    ...row,
                    current_crisis_level: currentCrisis,
                    the_end_table: row.table === 4 && currentCrisis === 4,
                  };
              });
//              console.log(possibleRNGData);
            toto.push(possibleRNGData[0]);
        }
        RandomMod --;
        LimitLevel --;
    }

//    console.log(RNG);console.log(toto);

    return toto;
}

async function main() {
  let possibleRNG  = calculPossibleRNG();
  console.log(possibleRNG);
//  let possibleRNGData = RNGData.filter(rng => possibleRNG.includes(rng.limitLevel))
//  .map((row) => {
//      return {
//        ...row,
//        the_end_table: row.table === 4 && row.current_crisis_level === 4,
//      };
//  });
//  console.log(possibleRNGData);
  let spellOrder = 1;
  const spellList = [];
  const blackDots = possibleRNG
        .filter((row) => row.the_end_table)
        .map((row) => row.rng);

  console.log(blackDots);

  let responses;
  while (true) {
    const spellResponse = await askSpell(spellOrder, possibleRNG);
    if (spellResponse == "The End (1)") {
        console.log("GO GO GO !!!!");
        return;
    }
    spellList.push(spellResponse);
    console.log(spellList);
    possibleRNG = findInfos(spellList, possibleRNG);
    if (possibleRNG.length === 0) {
        console.log("Nothing found for this spell combination, please Skip Turn and restart");
        return;
    }
    if (possibleRNG.length === 1) break;
    if (
      possibleRNG.every((response) => {
        return (
          response.table === possibleRNG[0].table &&
          response.entry === possibleRNG[0].entry
        );
      })
    ) {
      break;
    }
    console.dir({ possibleRNG }, { depth: null });
    spellOrder++;
  }
  console.dir({ possibleRNG, "Seen Spells": spellOrder }, { depth: null });

      const startTable = possibleRNG[0].table;
      const startEntry = possibleRNG[0].entry;

  const startRng = possibleRNG.find(({ limitLevel, rng, table, entry, the_end_table }) => {
    return table === startTable && entry === startEntry;
  });
  console.log({ startRng });
  if (typeof startRng == 'undefined') {
    console.log("Nothing found for this spell combination, please Skip Turn and restart");
    return;
  }

  let currentRng;
   if (possibleRNG.length == 1) {
    currentRng = startRng;
    currentRng.entry = currentRng.entry + spellOrder - 1
   } else {
    currentRng = possibleRNG.find(
    ({ limitLevel, rng, table, entry, the_end_table  }) => rng === (startRng.rng + spellOrder * 4) % 256);
  }
  if (typeof currentRng == 'undefined') {
    console.log("Nothing found for this spell combination, please Skip Turn and restart");
    return;
  }
  console.log({ currentRng });
  const rng = (currentRng.rng + spellOrder * 4) % 256;
//  currentRng.rng;
  if (currentRng.table === 4 && currentRng.current_crisis_level === 4) {
        let delta = 183 - rng;
        if (delta < 0) delta += 256;
        console.log(`do-over x${delta / 4}`);
        return;
      }

  const blackDot =
    blackDots.find((blackDot) => {
      return blackDot >= rng;
    }) || blackDots[0];

    if (currentRng.table === 4) {
          const doOver1 = (blackDot - 4 - rng) / 4;
          const skipTurn = 4;
          let delta = 183 - blackDot;
          if (delta < 0) delta += 256;
          const doOver2 = delta / 4 - 1;
           console.log( `do-over \tx${doOver1} \nskip-turn \tx${skipTurn} \ndo-over \tx${doOver2}`);
           return;
        }
  let TheEnd =
    (179 - blackDot) / 4 < 0
      ? (179 - blackDot + 256) / 4
      : (179 - blackDot) / 4;
  console.log({ blackDot });
  const delta = blackDot - rng < 0 ? blackDot - rng + 256 : blackDot - rng;
  let doOver = Math.floor(delta / 4);
  let skipTurn = delta % 4;
  if (blackDots.includes(startRng.rng)) {
    doOver = 0;
    skipTurn = 0;
    TheEnd =
      (179 - currentRng.rng) / 4 + 1 < 0
        ? (179 - currentRng.rng) / 4 + 65
        : (179 - currentRng.rng) / 4 + 1;
  }
  console.log(
    `Do Over : ${doOver}, Skip Turn : ${skipTurn}, Do Over : ${TheEnd}`
  );
}

function findInfos(spellList, possibleRNG) {
  const responses = [];
  // TODO Check RNG Deja disponible
  tablesData.forEach((table, tableIndex) => {
    table.forEach((crisis, crisisIndex) => {
      const matches = spellsMatchCrisis(spellList, crisis);
      if (matches !== false) {
        matches.forEach((match) => {
          responses.push({
            table: tableIndex + 1,
            crisis: crisisIndex + 1,
            entry: match + 1,
          });
        });
      }
    });
  });
  let toto = []
    responses.forEach((response) => {
        let found = possibleRNG.find(rng => rng.table === response.table && rng.current_crisis_level === response.crisis && rng.entry === response.entry);
        if ( typeof found !== 'undefined' ) {
            toto.push(found);
        }
    });
   console.dir({ toto }, { depth: null });
//   console.dir({ possibleRNG }, { depth: null });
//   console.dir({ responses }, { depth: null });
  return toto;
  // if (responses.length === 1) process.kill();
}

function spellsMatchCrisis(spellList, crisis) {
  const spell1Exists = crisis.find((spell) => spell === spellList[0]);
  if (!spell1Exists) return false;
  // const startIndex = crisis.findIndex(spell => spell === spellList[0])
  // find all start indexes
  const startIndexesCandidates = crisis.reduce((indexes, spell, spellIndex) => {
    if (spellList[0] === spell) {
      indexes.push(spellIndex);
    }
    return indexes;
  }, []);
  if (spellList.length === 1) return startIndexesCandidates;

  const startIndexes = [...startIndexesCandidates];
  startIndexesCandidates.forEach((startIndex, x) => {
    for (var i = 1; i < spellList.length; i++) {
      const spell = spellList[i];
      const index = startIndex + i > 63 ? startIndex + i - 64 : startIndex + i;
      if (spell !== crisis[index]) {
        startIndexes[x] = null;
        continue;
      }
    }
  });
  return startIndexes.filter((v) => v !== null);
}

function createAutocompleteSpellList(tablesData) {
  const spellList = [];
  tablesData.forEach((spell) => {
    const spellExists = spellList.find(
            (spellFromList) => spell === spellFromList
          );
          if (!spellExists) {
            spellList.push(spell);
          }
  });
//  tablesData.forEach((table) => {
//    table.forEach((crisis) => {
//      crisis.forEach((spell) => {
//        const spellExists = spellList.find(
//          (spellFromList) => spell === spellFromList
//        );
//        if (!spellExists) {
//          spellList.push(spell);
//        }
//      });
//    });
//  });
  return spellList;
}

async function askSpell(spellOrder, possibleRNG) {
    let tables = []
     possibleRNG.forEach(rng => {
//        console.log(rng);
//        console.log(tablesData[rng.table - 1][rng.current_crisis_level - 1][rng.entry - 1 + spellOrder - 1]);
        tables.push(tablesData[rng.table - 1][rng.current_crisis_level - 1][rng.entry - 1 + spellOrder -1]);
     });
    console.log(tables);
  const spellPrompt = new AutoComplete({
    message: `What is your #${spellOrder} slot spell ?`,
    limit: 10,
    initial: 2,
    choices: createAutocompleteSpellList(tables),
  });

  const spellResponse = await spellPrompt.run();
  return spellResponse;
}

//const blackDots = [43, 79, 91, 175, 179, 187, 207, 231, 251].sort(
//  (a, b) => a > b
//);


main();
