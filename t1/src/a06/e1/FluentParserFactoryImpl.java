package a06.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class FluentParserFactoryImpl implements FluentParserFactory{

    @Override
    public FluentParser<Integer> naturals() {
        return new FluentParser<Integer>() {
            int cont = 0;
            @Override
            public FluentParser<Integer> accept(Integer value) {
               if (cont == value) {
                cont ++;
                return this;
               } else {
                throw new IllegalStateException("Cannot accept this value");
               }
            }
            
        };
    }

    @Override
    public FluentParser<List<Integer>> incrementalNaturalLists() {
        return new FluentParser<List<Integer>>() {
            int dim = 0;
            List<Integer> list = new ArrayList<>();
            @Override
            public FluentParser<List<Integer>> accept(List<Integer> value) {
                if (this.dim == value.size() && this.dim == 0) {
                    return this;
                } else if (this.dim + 1 == value.size()) {
                    if(this.dim == value.getLast()) {
                        this.list.addLast(value.getLast());
                        dim = dim + 1;
                        return this;
                    }
                 }
                    throw new IllegalStateException("Cannot accept this value");
            }
            
        };
    }

    @Override
    public FluentParser<Integer> repetitiveIncrementalNaturals() {
        
        return new FluentParser<Integer>() {
            List<Integer> repetitions = new ArrayList<>();
            int cont = 0;
            int next;
            boolean restart = false;
            @Override
            public FluentParser<Integer> accept(Integer value) {
                if (this.repetitions.size() == 0) {
                    repetitions.addLast(value);
                    cont ++;
                    return this;
                } else {
                    if(cont == this.repetitions.size() && value == 0) {
                        next = this.repetitions.getLast() + 1;
                        cont = 0;
                        return this;
                    } else if (cont == 0 && restart == false && value == next) {
                        this.repetitions.addLast(value);
                        restart = true;
                        return this;
                    } else if (cont == 0 && restart == true) {
                        if (value == cont) {
                            cont ++;
                            restart = false;
                            return this;
                        }
                    }
                }
                throw new IllegalStateException("Cannot accept this value");
        }
        };
    }

    @Override
    public FluentParser<String> repetitiveIncrementalStrings(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'repetitiveIncrementalStrings'");
    }

    @Override
    public FluentParser<Pair<Integer, List<String>>> incrementalPairs(int i0, UnaryOperator<Integer> op, String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'incrementalPairs'");
    }
    
}
